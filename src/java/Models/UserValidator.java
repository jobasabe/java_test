package Models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author José Basabe
 */
public class UserValidator implements Validator{
    private Pattern pat;
    private Matcher mat;
    private List data;
    private List restrictedWords;
    private boolean showSuggest;

    public UserValidator(List data, List restrictedWrods) {
        this.data = data;
        this.restrictedWords = restrictedWrods;
    }   
    
    public boolean isShowSuggest() {
        return showSuggest;
    }

    @Override
    public boolean supports(Class<?> type) {
        return User.class.isAssignableFrom(type);
    }     
    
    @Override
    public void validate(Object o, Errors errors) {
        User u = (User) o;
        this.showSuggest=false;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "UserName", "required.userName",
                "User name could not be empty.");  
        if(u.getUserName()!=null && !u.getUserName().isEmpty()){
            //Valida que el nombre usuario sea mayor o igual a 6.
            if (!isLongEnough(u.getUserName())){
                errors.rejectValue("userName","userName.incorrect", "User name should be"
                        + " at least 6 characters long.");
            }
            
            //Valida si el nombre de usuario no este repetido.
            if(this.isTaken(u.getUserName(),data)){
                this.showSuggest=true;
                errors.rejectValue("userName","userName.incorrect","User name is"
                        + " already taken.");
            }
            if(this.isRestricted(u.getUserName(),restrictedWords)){
                errors.rejectValue("userName","userName.incorrect","User name can not be"
                        + " a restricted word.");
            }            
        }
    }

    private boolean isLongEnough(String userName){
        return (userName.length()>=6);
    }
    
    private boolean isTaken(String userName, List list){ 
        boolean taken=false;
        for (Object us: list){
            Map m= (Map)us;
            if( m.get("userName").equals(userName)){
                taken=true;                
            }
        }
        return taken;
    }
    
    private boolean isRestricted(String userName, List list){ 
        boolean restricted=false;
        for (Object us: list){
            Map m= (Map)us;
            if( m.get("word").equals(userName)){
                restricted=true;
            }
        }
        return restricted;
    }
    
    public List<String> suggested(String userName){
        List suggestedWords = new ArrayList();
        for (int i = 0; i < 2; i++) {
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            int monthNumber = cal.get(Calendar.MONTH) + 1;
            String month = new SimpleDateFormat("MMMM").format(date);
            this.addWordToList(userName.substring(0, 2) + year, suggestedWords);
            this.addWordToList(userName.substring(0, 2) + "_" + year, suggestedWords);
            this.addWordToList(userName + userName, suggestedWords);
            this.addWordToList(userName + "_" + userName, suggestedWords);
            this.addWordToList(userName + month, suggestedWords);
            this.addWordToList(userName + "_" + month, suggestedWords);
            this.addWordToList(userName + "_" + month + year, suggestedWords);
            this.addWordToList(userName + month + year, suggestedWords);
            this.addWordToList(userName + "_" + monthNumber + year, suggestedWords);
            this.addWordToList(userName + monthNumber + year, suggestedWords);
            for (int t = 0; t < 13; t++) {
                this.addWordToList(userName + (t), suggestedWords);
            }
        }
        return suggestedWords;
    } 
    
    private void addWordToList(String word, List list){
        if (list.size()<14 ){
            if (!isRestricted(word, this.restrictedWords) && !isTaken(word, this.data)
                    && !list.contains(word)) {
                list.add(word);
            }
        }
    }
}
