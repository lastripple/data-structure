import com.troublemaker.homework.PolishExpressions;
import org.junit.jupiter.api.Test;

/**
 * @BelongsProject: data_structure
 * @BelongsPackage: PACKAGE_NAME
 * @Author: troublemaker
 * @CreateTime: 2022-05-06  17:56
 * @Description: TODO
 * @Version: 1.0
 */
public class HomeworkTest {
    @Test
    public void test0() {
        PolishExpressions expressions = new PolishExpressions();
        System.out.println(expressions.doCalculate("4 5 * 8 - 60 + 8 2 / +"));
    }
}

