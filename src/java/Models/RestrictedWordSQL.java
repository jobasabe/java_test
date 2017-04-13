package Models;

/**
 *
 * @author José Basabe
 */
public class RestrictedWordSQL {
    public static final String INSERT="insert into restricted_words (word) value (?);";
    public static final String DELETE="delete from restricted_words where word= ?;";
    public static final String SELECT="select * from restricted_words;";    
}
