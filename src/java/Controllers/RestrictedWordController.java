package Controllers;

import Models.DataSource;
import Models.RestrictedWord;
import Models.RestrictedWordSQL;
import Models.RestrictedWordValidator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
public class RestrictedWordController {
    DataSource source;
    private JdbcTemplate jdbc;
    private RestrictedWordValidator restrictedWordValidator;
    
    public RestrictedWordController(){
        this.source = new DataSource();        
        this.jdbc=new JdbcTemplate(source.connect());        
    }
    
    @RequestMapping("restricted_word_index.htm")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView();
        List data= jdbc.queryForList(RestrictedWordSQL.SELECT);
        this.source.closeConnection();
        mav.addObject("data",data);
        mav.setViewName("restrictedWordIndex");
        return mav;
    }
    
    @RequestMapping (value="new_restricted_word.htm", method=RequestMethod.GET)
    public ModelAndView newWord(){
        ModelAndView mav =new ModelAndView();
        mav.setViewName("newRestrictedWord");
        mav.addObject("word", new RestrictedWord());
        return mav;
    }
    
    @RequestMapping(value="new_restricted_word.htm", method=RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("word") RestrictedWord rW, 
                        BindingResult result, SessionStatus status){
        this.restrictedWordValidator=new RestrictedWordValidator(jdbc.queryForList(RestrictedWordSQL.SELECT));
        this.restrictedWordValidator.validate(rW, result);
        if(result.hasErrors()){
            ModelAndView mav =new ModelAndView();
            mav.setViewName("newRestrictedWord");
            mav.addObject("word", new RestrictedWord());
            return mav;
        }else{            
            this.jdbc.update(RestrictedWordSQL.INSERT,rW.getWord());
            this.source.closeConnection();
            return new ModelAndView("redirect:/restricted_word_index.htm");
        }        
    }
    
    @RequestMapping("delete_restricted_word.htm")
    public ModelAndView delete(HttpServletRequest request){        
        this.jdbc.update(RestrictedWordSQL.DELETE,request.getParameter("word"));
        this.source.closeConnection();
        return new ModelAndView("redirect:/restricted_word_index.htm");
    }
    
}