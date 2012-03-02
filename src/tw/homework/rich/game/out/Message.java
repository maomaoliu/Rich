/**
 * 
 */
package tw.homework.rich.game.out;

import tw.homework.rich.game.Game;

/**
 * @author noam yacht2005@gmail.com Created at：2012-1-11
 */
public class Message {

	public static final String HELP_USAGE = "用法：";
	public static final String ERROR = "ERROR";

	public static final String ROLL_COMMOND = "roll";
	public static final String BLOCK_COMMOND = "block";
	public static final String BOMB_COMMOND = "bomb";
	public static final String ROBOT_COMMOND = "robot";
	public static final String SELL_COMMOND = "sell";
	public static final String SELLTOOL_COMMOND = "selltool";
	public static final String QUERY_COMMOND = "query";
	public static final String HELP_COMMOND = "help";
	public static final String QUIT_COMMOND = "quit";

	public static final String ROLL_HELP = "roll"
			+ System.getProperty("line.separator") + "掷骰子命令，随机行走1~6步。";
	public static final String BLOCK_HELP = "block n"
			+ System.getProperty("line.separator")
			+ "玩家拥有路障后，可将路障放置到离当前位置前后10步的距离，任一玩家经过路障，都将被拦截。该道具一次有效。n表示前后的相对距离，负数表示后方。";
	public static final String BOMB_HELP = "bomb n"
			+ System.getProperty("line.separator")
			+ "玩家拥有炸弹后，可将炸弹放置到离当前位置前后10步的距离，任一玩家经过在该位置，将被炸伤，送往医院，住院三天。n表示前后的相对距离，负数表示后方。";
	public static final String ROBOT_HELP = "robot"
			+ System.getProperty("line.separator")
			+ "使用该道具，可清扫前方路面上10步以内的其它道具，如炸弹、路障。";
	public static final String SELL_HELP = "sell x"
			+ System.getProperty("line.separator")
			+ "出售自己的房产，x表示地图上的绝对位置，即地产的编号。";
	public static final String SELLTOOL_HELP = "selltool x"
			+ System.getProperty("line.separator") + "出售道具，x表示道具编号。";
	public static final String QUERY_HELP = "query"
			+ System.getProperty("line.separator") + "显示自家资产信息。";
	public static final String HELP_HELP = "help"
			+ System.getProperty("line.separator") + "查看命令帮助。";
	public static final String QUIT_HELP = "quit"
			+ System.getProperty("line.separator") + "退出游戏。";

	public static final String QIANFUREN = "钱夫人";
	public static final String ATUBO = "阿土伯";
	public static final String SUNXIAOMEI = "孙小美";
	public static final String JINBEIBEI = "金贝贝";
	public static final String QIANFUREN_ABB = "Q";
	public static final String ATUBO_ABB = "A";
	public static final String SUNXIAOMEI_ABB = "S";
	public static final String JINBEIBEI_ABB = "J";

	public static final String NO_OWNER_LAND = "0";
	public static final String EMPTY_LAND = "0";
	public static final String RANK_1_LAND = "1";
	public static final String RANK_2_LAND = "2";
	public static final String RANK_3_LAND = "3";
	public static final String EMPTY_LAND_NAME = "空地";
	public static final String RANK_1_LAND_NAME = "茅屋";
	public static final String RANK_2_LAND_NAME = "洋房";
	public static final String RANK_3_LAND_NAME = "摩天楼";
	public static final String ERROR_LAND = "E";
	public static final String START_POSITION = "S";
	public static final String HOSPITAL_POSITION = "H";
	public static final String PROP_POSITION = "T";
	public static final String GIFT_POSITION = "G";
	public static final String MAGIC_POSITION = "M";
	public static final String PRISON_POSITION = "P";
	public static final String MINERAL_POSITION = "$";

	public static final String IF_NOT_CHANGE_INIT_CASH = "初始资金"
			+ Game.DEFAULT_INIT_CASH + "，确认请按Y，重设请设N。";
	public static final String ASK_TO_SET_INIT_CASH = "请设置初始资金，范围"
			+ Game.MIN_INIT_CASH + "～" + Game.MAX_INIT_CASH + "。";

	public static final String TO_SET_PLAYERS = "请选择" + Game.MIN_PLAYERS_NUM
			+ "~" + Game.MAX_PLAYERS_NUM
			+ "位不重复玩家，输入编号即可。(1.钱夫人; 2.阿土伯; 3.孙小美; 4.金贝贝)。";

	public static final String ILLEGAL_INPUT_TIPS = "输入错误，请重新输入。";

	public static final String QUIT_GAME_TIPS = "您选择了退出游戏，正在退出....";
	public static final String CASH_NOT_ENOUGH_TIPS = "现金不足，不能购买";

	public static final String PROP_BLOCK_MARK = "#";
	public static final String PROP_BLOCK_NAME = "路障";
	public static final String PROP_BOMB_MARK = "@";
	public static final String PROP_BOMB_NAME = "炸弹";
	public static final String PROP_ROBOT_NAME = "机器娃娃";
	public static final String PROP_EXIT_COMMAND = "f";

	public static final String GIFT_MONEY_NAME = "奖金";
	public static final String GIFT_POINTS_NAME = "点数卡";
	public static final String GIFT_FUSHEN_NAME = "福神";

	public static final String REACH_BLOCK_TIPS = "碰到路障啦，只能停下来。";
	public static final String REACH_BOMB_TIPS = "碰到炸弹啦，送到医院去，得休息"
			+ Game.HOSPITAL_STOP_TIMES + "天养养病。";
	public static final String PROP_NOT_FOUND_TIPS = "道具不存在。";
	public static final String GIFT_NOT_FOUND_TIPS = "礼品不存在。";

	public static final String MONEY_UNIT = "元";
	public static final String POINT_UNIT = "点";
	public static final String BUILDING_UNIT = "处";
	public static final String PROP_UNIT = "个";
	public static final String ROLL_UNIT = "步";
	public static final String TIMES_UNIT = "次";
	public static final String SEPERATOR = ";";

	public static final String ASK_TIPS = "（Y/N）?";
	public static final String ASK_FOR_BUY_LAND = "是否购买该处空地，";
	public static final String ASK_FOR_UPDATE_LAND = "是否升级该处地产，";

	public static final String YES = "y";
	public static final String NO = "n";
	public static final String OWNER_IS_MISSING = "土地所有者在医院或监狱。";
	public static final String EXEMPTTAX = "免除过路费。";
	public static final String HAS_FUSHEN = "福神附身。";
	public static final String GAME_OVER = "游戏结束。";
	public static final String WINNER_TIPS = "胜利者是：";

	public static final String QUERY_INFO1 = "显示自家资产信息：";
	public static final String QUERY_INFO2 = "资 金:";
	public static final String QUERY_INFO3 = "点数:";
	public static final String QUERY_INFO4 = "地产:";
	public static final String QUERY_INFO5 = "道具:";

	public static final String LAND_NOT_YOU_TIPS = "该处土地不是你的哦，请重新输入。";
	public static final String WELCOME_GIFT = "欢迎光临礼品屋，请选择一件您喜欢的礼品。";
	public static final String CURRENT_PLAYER = "当前玩家：";
	public static final String ROLL_RESULT = "通过掷筛子，您可前进";
	public static final String STOP_TIPS = "该玩家本次轮空。";
	public static final String SELL_LAND_TIPS = "号土地卖出成功，获得";
	public static final String SELL_PROP_TIPS1 = "道具\"";
	public static final String SELL_PROP_TIPS2 = "\"卖出成功，获得";
	public static final String BUY_LAND_TIPS = "号土地购买成功，消耗资金";
	public static final String CONSTRUCT_OUT_OF_TIMES_TIPS = "土地已经开发完毕，不能再开发啦...";
	public static final String LAND_OWNER_NOT_FOUND_TIPS = "土地还没有所有者...";
	public static final String PLAYER_BANKRUPT_TIPS = "破产啦";
	public static final String PAY_TAXES_TIPS1 = "路过";
	public static final String PAY_TAXES_TIPS2 = "的土地，需缴纳税款";
	public static final String PAY_TAXES_TIPS3 = "失去资金";
	public static final String PAY_TAXES_TIPS4 = "获得资金";
	public static final String UPDATE_LAND_TIPS1 = "号土地升级成功，升级为";
	public static final String UPDATE_LAND_TIPS2 = "，消耗资金";

	public static final String GAME_LOGIC_ERROR = "程序逻辑错误，请联系开发人员：yacht2005@gmail.com(毛毛)";
	public static final String SMALL_ERROR_TIPS = "但问题不大，可以继续。";

	public static final String REACH_PROP_TIPS = "欢迎光临道具屋， 请选择您所需要的道具：";
	public static final String REACH_PROP_TIPS1 = "道具\t编号\t点数";
	public static final String POINTS_NOT_ENOUGH = "点数不足。";
	public static final String POINTS_NOT_ENOUGH1 = "您当前剩余的点数为";
	public static final String POINTS_NOT_ENOUGH2 = "，无法购买任何道具，自动退出道具屋。";
	public static final String POINTS_NOT_ENOUGH3 = "不足以购买道具";
	public static final String QUIT_PROP_TIPS = "退出道具屋。";
	public static final String PROP_REACH_MAX_TIPS1 = "您已拥有"
			+ Game.MAX_PROP_NUMBER + "个道具，达到最大值，自动退出道具屋。";
	public static final String PROP_REACH_MAX_TIPS2 = "您最多只能拥有"
			+ Game.MAX_PROP_NUMBER + "个道具。";
	public static final String BUY_PROP_TIPS = "您已购买这些道具，消耗点数";

	public static final String CONSTRUCTING_TIPS = "建设中，暂不开放。";
	public static final String REACH_MINERAL_TIPS = "到达旷地，您获取点数";
	public static final String POSITION_IS_USING_TIPS = "该位置有玩家或已防止道具。";

	public static final String REACH_GIFT_TIPS = "欢迎光临礼品屋，请选择一件您喜欢的礼品：";
	public static final String REACH_GIFT_TIPS1 = "礼品\t编号";
	public static final String QUIT_GIFT_TIPS1 = "输入错误";
	public static final String QUIT_GIFT_TIPS2 = "您获得了礼品";
	public static final String QUIT_GIFT_TIPS = "，退出礼品屋。";

	public static final String REACH_PRISON_TIPS = "一不小心到监狱了，被扣留"
			+ Game.PRISON_STOP_TIMES + "天。";
	protected static final String SHOW_HOW_TO_QUIT = "输入命令\"quit\"退出游戏。";
}
