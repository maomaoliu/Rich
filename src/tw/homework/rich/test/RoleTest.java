/**
 * 
 */
package tw.homework.rich.test;

import static org.junit.Assert.*;

import org.junit.Test;

import tw.homework.rich.game.exception.IllegalRoleCodeException;
import tw.homework.rich.game.player.Role;

/**
 * @author noam 
 *     yacht2005@gmail.com
 * Created at：2012-2-12
 */
public class RoleTest {

	@Test
	public void testGetRoleByCode() throws IllegalRoleCodeException {
		assertEquals(Role.QIANFUREN, Role.getRoleByCode(1));
		assertEquals(Role.ATUBO, Role.getRoleByCode(2));
		assertEquals(Role.SUNXIAOMEI, Role.getRoleByCode(3));
		assertEquals(Role.JINBEIBEI, Role.getRoleByCode(4));
	}
	
	@Test(expected=IllegalRoleCodeException.class)
	public void testGetRoleByCodeWithIllegal() throws IllegalRoleCodeException {
		Role.getRoleByCode(5);
	}

}
