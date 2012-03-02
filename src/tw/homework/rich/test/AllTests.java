/**
 * 
 */
package tw.homework.rich.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author noam 
 *     yacht2005@gmail.com
 * Created at：2012-2-19
 */
@RunWith(Suite.class)
@SuiteClasses({ GameTest.class, GameTest_HandleInput.class,
		InAndOutTestUtils.class, InitCashSettingTest.class,
		InitPlayersSettingTest.class, LandPositionTest.class,
		MapCreaterTest.class, PlayerTest.class, RoleTest.class })
public class AllTests {

}
