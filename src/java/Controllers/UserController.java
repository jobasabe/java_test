package Controllers;

import Models.DataSource;
import Models.RestrictedWordSQL;
import Models.User;
import Models.UserSQL;
import Models.UserValidator;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author José Basabe
 */
@Controller
public class UserController {
    private DataSource source;
    private JdbcTemplate jdbc;
    private UserValidator userValidator;
    
    public UserController(){
        this.source = new DataSource();
        this.jdbc=new JdbcTemplate(source.connect());        
    }
    
    @RequestMapping (value="user.htm", method=RequestMethod.GET)
    public ModelAndView newUser(){
        ModelAndView mav =new ModelAndView();
        mav.setViewName("user");
        mav.addObject("user", new User());
        return mav;
    }
    
    @RequestMapping(value="user.htm", method=RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("user") User u, 
                        BindingResult result, SessionStatus status){
        this.userValidator= new UserValidator(jdbc.queryForList(UserSQL.SELECT),
                jdbc.queryForList(RestrictedWordSQL.SELECT));
        this.userValidator.validate(u, result);
        if(result.hasErrors()){
            ModelAndView mav =new ModelAndView();
            mav.setViewName("user");
            mav.addObject("user", new User());
            mav.addObject("showSuggested",this.userValidator.isShowSuggest()); 
            if(this.userValidator.isShowSuggest()){
                mav.addObject("suggests",this.userValidator.suggested(u.getUserName()));
            }
            return mav;
        }else{
            this.jdbc.update(UserSQL.INSERT,u.getUserName());
            this.source.closeConnection();
            return new ModelAndView("redirect:/index.htm");
        }         
    }
}