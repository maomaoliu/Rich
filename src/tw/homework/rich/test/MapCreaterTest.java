/**
 * 
 */
package tw.homework.rich.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tw.homework.rich.game.Game;
import tw.homework.rich.game.Map;
import tw.homework.rich.game.MapCreater;
import tw.homework.rich.game.position.GiftPosition;
import tw.homework.rich.game.position.HospitalPosition;
import tw.homework.rich.game.position.LandPosition;
import tw.homework.rich.game.position.MagicPosition;
import tw.homework.rich.game.position.MineralPosition;
import tw.homework.rich.game.position.Position;
import tw.homework.rich.game.position.PrisonPosition;
import tw.homework.rich.game.position.PropPosition;
import tw.homework.rich.game.position.StartPosition;

/**
 * @author noam 
 *     yacht2005@gmail.com
 * Created at：2012-2-19
 */
public class MapCreaterTest {

	static Game game;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		InAndOutTestUtils.setUp();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		InAndOutTestUtils.tearDown();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		InAndOutTestUtils.clearPipes();
		game = new Game();
	}

	/**
	 * Test method for {@link tw.homework.rich.game.MapCreater#createMap()}.
	 */
	@Test
	public void testCreateMap(){
		Map map = MapCreater.createMap();
		for(int i = 0; i<70; i++){
			assertEquals(i, map.getPosition(i).getNumber());
			if(i==0){
				assertTrue(map.getPosition(i) instanceof StartPosition);
				assertEquals("S", map.getPosition(i).getMark());
			}else if(i>=1 && i<=13){
				assertTrue(map.getPosition(i) instanceof LandPosition);
				LandPosition land = (LandPosition)map.getPosition(i);
				assertEquals(200, land.getInitPrice());
				assertEquals(3, land.getMaxConstructTimes());
				assertEquals("0", land.getMark());
			}else if(i==14){
				assertTrue(map.getPosition(i) instanceof HospitalPosition);
				assertEquals("H", map.getPosition(i).getMark());
			}else if(i>=15 && i<=27){
				assertTrue(map.getPosition(i) instanceof LandPosition);
				LandPosition land = (LandPosition)map.getPosition(i);
				assertEquals(200, land.getInitPrice());
				assertEquals(3, land.getMaxConstructTimes());
				assertEquals("0", land.getMark());
			}else if(i==28){
				assertTrue(map.getPosition(i) instanceof PropPosition);
				assertEquals("T", map.getPosition(i).getMark());
			}else if(i>=29&& i<=34){
				assertTrue(map.getPosition(i) instanceof LandPosition);
				LandPosition land = (LandPosition)map.getPosition(i);
				assertEquals(500, land.getInitPrice());
				assertEquals(3, land.getMaxConstructTimes());
				assertEquals("0", land.getMark());
			}else if(i==35){
				assertTrue(map.getPosition(i) instanceof GiftPosition);
				assertEquals("G", map.getPosition(i).getMark());
			}else if(i>=36&& i<=48){
				assertTrue(map.getPosition(i) instanceof LandPosition);
				LandPosition land = (LandPosition)map.getPosition(i);
				assertEquals(300, land.getInitPrice());
				assertEquals(3, land.getMaxConstructTimes());
				assertEquals("0", land.getMark());
			}else if(i==49){
				assertTrue(map.getPosition(i) instanceof PrisonPosition);
				assertEquals("P", map.getPosition(i).getMark());
			}else if(i>=50&& i<=62){
				assertTrue(map.getPosition(i) instanceof LandPosition);
				LandPosition land = (LandPosition)map.getPosition(i);
				assertEquals(300, land.getInitPrice());
				assertEquals(3, land.getMaxConstructTimes());
				assertEquals("0", land.getMark());
			}else if(i==63){
				assertTrue(map.getPosition(i) instanceof MagicPosition);
				assertEquals("M", map.getPosition(i).getMark());
			}else if(i==64){
				assertTrue(map.getPosition(i) instanceof MineralPosition);
				MineralPosition position = (MineralPosition)map.getPosition(i);
				assertEquals(20, position.getPoints());
				assertEquals("$", position.getMark());
			}else if(i==65){
				assertTrue(map.getPosition(i) instanceof MineralPosition);
				MineralPosition position = (MineralPosition)map.getPosition(i);
				assertEquals(80, position.getPoints());
				assertEquals("$", position.getMark());
			}else if(i==66){
				assertTrue(map.getPosition(i) instanceof MineralPosition);
				MineralPosition position = (MineralPosition)map.getPosition(i);
				assertEquals(100, position.getPoints());
				assertEquals("$", position.getMark());
			}else if(i==67){
				assertTrue(map.getPosition(i) instanceof MineralPosition);
				MineralPosition position = (MineralPosition)map.getPosition(i);
				assertEquals(40, position.getPoints());
				assertEquals("$", position.getMark());
			}else if(i==68){
				assertTrue(map.getPosition(i) instanceof MineralPosition);
				MineralPosition position = (MineralPosition)map.getPosition(i);
				assertEquals(80, position.getPoints());
				assertEquals("$", position.getMark());
			}else if(i==69){
				assertTrue(map.getPosition(i) instanceof MineralPosition);
				MineralPosition position = (MineralPosition)map.getPosition(i);
				assertEquals(60, position.getPoints());
				assertEquals("$", position.getMark());
			}
		}
	}
	/**
	 * Test method for {@link tw.homework.rich.game.MapCreater#getNextNPosition(int, int)}.
	 */
	@Test
	public void testGetNextNPosition() {
		Position resultPositon = MapCreater.getNextNPosition(0, 5);
		assertEquals(5, resultPositon.getNumber());
		resultPositon = MapCreater.getNextNPosition(68, 5);
		assertEquals(3, resultPositon.getNumber());
	}

	/**
	 * Test method for {@link tw.homework.rich.game.MapCreater#getHospital_num()}.
	 */
	@Test
	public void testGetHospital_num() {
		assertEquals(14, MapCreater.getHospital_num());
	}

	/**
	 * Test method for {@link tw.homework.rich.game.MapCreater#getPrison_num()}.
	 */
	@Test
	public void testGetPrison_num() {
		assertEquals(49, MapCreater.getPrison_num());
	}

}
