package Models;

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
public class RestrictedWordValidator implements Validator{
    //Formato invalido del valor.
    private static final String WORD_PATTERN="[^a-zA-Z]+";
    private Pattern pat;
    private Matcher mat;
    private List data;

    public RestrictedWordValidator(List data) {
        this.data=data;
    }
    
    

    @Override
    public boolean supports(Class<?> type) {
        return RestrictedWord.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RestrictedWord rW = (RestrictedWord) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "word", "required.word",
                "Word is required. Plis write a restricted word."); 
        
        //Valida si la palabra no este repetido.
        if(this.exist(rW.getWord(),data)){
            errors.rejectValue("word","word.incorrect","word"
                    + " already exist. Plis try with other word.");
        }
            
        if(rW.getWord()!=null && !rW.getWord().isEmpty()){
            this.pat= Pattern.compile(WORD_PATTERN);
            this.mat=pat.matcher(rW.getWord());
            if(this.mat.find()){
                errors.rejectValue("word","word.incorrect", "Invalid word.");
            }
        }
    } 
    private boolean exist(String userName, List list){ 
        boolean taken=false;
        for (Object us: list){
            Map m= (Map)us;
            if( m.get("word").equals(userName)){
                taken=true;                
            }
        }
        return taken;
    }
}