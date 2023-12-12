package json;

import java.util.Iterator;

import org.json.*;
// https://stackoverflow.com/questions/3280353/how-to-import-a-jar-in-eclipse

import number.IsNumber;

//Just downloaded jar...
//https://github.com/stleary/JSON-java

public class parseJSON {

	public static void main(String args[]) {
		
		String INPUT_DAY_KEY = "12";

		String jsonString = "{\"members\":{\"417433\":{\"completion_day_level\":{},\"stars\":0,\"global_score\":0,\"id\":417433,\"last_star_ts\":0,\"name\":\"frigley1\",\"local_score\":0},\"119856\":{\"last_star_ts\":0,\"id\":119856,\"global_score\":0,\"completion_day_level\":{},\"stars\":0,\"local_score\":0,\"name\":\"imnottommy\"},\"995421\":{\"local_score\":0,\"name\":\"Quin Millard\",\"id\":995421,\"last_star_ts\":0,\"global_score\":0,\"completion_day_level\":{},\"stars\":0},\"116481\":{\"last_star_ts\":1702361496,\"id\":116481,\"global_score\":0,\"completion_day_level\":{\"9\":{\"1\":{\"star_index\":1843732,\"get_star_ts\":1702100773},\"2\":{\"get_star_ts\":1702101122,\"star_index\":1844963}},\"1\":{\"1\":{\"star_index\":106720,\"get_star_ts\":1701435754},\"2\":{\"star_index\":109881,\"get_star_ts\":1701436694}},\"12\":{\"1\":{\"star_index\":2290383,\"get_star_ts\":1702361496}},\"5\":{\"1\":{\"star_index\":1062530,\"get_star_ts\":1701755663},\"2\":{\"star_index\":1251415,\"get_star_ts\":1701837613}},\"4\":{\"1\":{\"star_index\":790865,\"get_star_ts\":1701668452},\"2\":{\"get_star_ts\":1701669578,\"star_index\":796863}},\"11\":{\"1\":{\"get_star_ts\":1702273987,\"star_index\":2147476},\"2\":{\"get_star_ts\":1702275640,\"star_index\":2152131}},\"6\":{\"1\":{\"get_star_ts\":1701839734,\"star_index\":1261527},\"2\":{\"get_star_ts\":1701840025,\"star_index\":1264949}},\"8\":{\"1\":{\"star_index\":1668518,\"get_star_ts\":1702014521},\"2\":{\"get_star_ts\":1702017016,\"star_index\":1676399}},\"3\":{\"2\":{\"get_star_ts\":1701624589,\"star_index\":677538},\"1\":{\"star_index\":676554,\"get_star_ts\":1701624315}},\"7\":{\"1\":{\"star_index\":1499837,\"get_star_ts\":1701933637},\"2\":{\"get_star_ts\":1701957728,\"star_index\":1558409}},\"2\":{\"1\":{\"get_star_ts\":1701495863,\"star_index\":286557},\"2\":{\"star_index\":289486,\"get_star_ts\":1701496389}},\"10\":{\"1\":{\"star_index\":2005678,\"get_star_ts\":1702188635},\"2\":{\"star_index\":2100610,\"get_star_ts\":1702240178}}},\"stars\":23,\"local_score\":1086,\"name\":\"mozesmagyar\"},\"374494\":{\"last_star_ts\":0,\"id\":374494,\"global_score\":0,\"stars\":0,\"completion_day_level\":{},\"local_score\":0,\"name\":null},\"250349\":{\"local_score\":990,\"name\":\"David McFadzean\",\"id\":250349,\"last_star_ts\":1702313060,\"global_score\":0,\"stars\":22,\"completion_day_level\":{\"2\":{\"2\":{\"get_star_ts\":1701573466,\"star_index\":527844},\"1\":{\"star_index\":527245,\"get_star_ts\":1701572913}},\"10\":{\"1\":{\"get_star_ts\":1702221098,\"star_index\":2062763},\"2\":{\"get_star_ts\":1702229599,\"star_index\":2079937}},\"6\":{\"2\":{\"get_star_ts\":1701877102,\"star_index\":1384795},\"1\":{\"get_star_ts\":1701874312,\"star_index\":1377163}},\"11\":{\"2\":{\"get_star_ts\":1702313060,\"star_index\":2224293},\"1\":{\"get_star_ts\":1702308784,\"star_index\":2216109}},\"3\":{\"1\":{\"star_index\":759087,\"get_star_ts\":1701655810},\"2\":{\"star_index\":760401,\"get_star_ts\":1701656910}},\"8\":{\"1\":{\"get_star_ts\":1702044795,\"star_index\":1744345},\"2\":{\"star_index\":1820480,\"get_star_ts\":1702086600}},\"7\":{\"2\":{\"star_index\":1623626,\"get_star_ts\":1701985838},\"1\":{\"star_index\":1583485,\"get_star_ts\":1701968138}},\"5\":{\"1\":{\"star_index\":1155763,\"get_star_ts\":1701791339},\"2\":{\"get_star_ts\":1701916597,\"star_index\":1465291}},\"4\":{\"1\":{\"star_index\":908512,\"get_star_ts\":1701698474},\"2\":{\"star_index\":926957,\"get_star_ts\":1701703375}},\"1\":{\"1\":{\"star_index\":252054,\"get_star_ts\":1701480316},\"2\":{\"star_index\":387570,\"get_star_ts\":1701522787}},\"9\":{\"1\":{\"get_star_ts\":1702130674,\"star_index\":1914309},\"2\":{\"get_star_ts\":1702130954,\"star_index\":1914961}}}},\"30841\":{\"completion_day_level\":{\"2\":{\"1\":{\"star_index\":290331,\"get_star_ts\":1701496565},\"2\":{\"get_star_ts\":1701497220,\"star_index\":293258}},\"10\":{\"1\":{\"get_star_ts\":1702190072,\"star_index\":2008419},\"2\":{\"get_star_ts\":1702247689,\"star_index\":2114811}},\"6\":{\"1\":{\"get_star_ts\":1701839458,\"star_index\":1257620},\"2\":{\"get_star_ts\":1701839654,\"star_index\":1260441}},\"11\":{\"1\":{\"star_index\":2281163,\"get_star_ts\":1702355613},\"2\":{\"star_index\":2281719,\"get_star_ts\":1702356440}},\"3\":{\"1\":{\"get_star_ts\":1701584710,\"star_index\":553138},\"2\":{\"get_star_ts\":1701586230,\"star_index\":557904}},\"8\":{\"1\":{\"get_star_ts\":1702014322,\"star_index\":1667713},\"2\":{\"star_index\":1671827,\"get_star_ts\":1702015469}},\"7\":{\"1\":{\"star_index\":1485795,\"get_star_ts\":1701928957},\"2\":{\"get_star_ts\":1702095011,\"star_index\":1826036}},\"12\":{\"1\":{\"get_star_ts\":1702360790,\"star_index\":2289158}},\"5\":{\"1\":{\"get_star_ts\":1701755414,\"star_index\":1061584},\"2\":{\"star_index\":1982455,\"get_star_ts\":1702165029}},\"4\":{\"1\":{\"star_index\":778403,\"get_star_ts\":1701666941},\"2\":{\"star_index\":789986,\"get_star_ts\":1701668305}},\"1\":{\"2\":{\"get_star_ts\":1701447685,\"star_index\":153391},\"1\":{\"star_index\":22808,\"get_star_ts\":1701411440}},\"9\":{\"2\":{\"star_index\":1835391,\"get_star_ts\":1702099258},\"1\":{\"get_star_ts\":1702098980,\"star_index\":1833112}}},\"stars\":23,\"global_score\":0,\"id\":30841,\"last_star_ts\":1702360790,\"name\":\"Benjamin Wuethrich\",\"local_score\":1081},\"137785\":{\"name\":\"vincentr21\",\"local_score\":0,\"completion_day_level\":{},\"stars\":0,\"global_score\":0,\"id\":137785,\"last_star_ts\":0},\"197423\":{\"completion_day_level\":{},\"stars\":0,\"global_score\":0,\"id\":197423,\"last_star_ts\":0,\"name\":null,\"local_score\":0},\"195611\":{\"local_score\":216,\"name\":\"Dumtard\",\"last_star_ts\":1701923702,\"id\":195611,\"global_score\":0,\"completion_day_level\":{\"3\":{\"1\":{\"star_index\":1471247,\"get_star_ts\":1701923702}},\"1\":{\"2\":{\"get_star_ts\":1701467952,\"star_index\":224439},\"1\":{\"star_index\":156247,\"get_star_ts\":1701448405}},\"2\":{\"2\":{\"get_star_ts\":1701635002,\"star_index\":711825},\"1\":{\"star_index\":533789,\"get_star_ts\":1701579403}}},\"stars\":5},\"1687990\":{\"stars\":0,\"completion_day_level\":{},\"global_score\":0,\"last_star_ts\":0,\"id\":1687990,\"name\":\"Sam Leitch\",\"local_score\":0},\"1060563\":{\"local_score\":0,\"name\":null,\"id\":1060563,\"last_star_ts\":0,\"global_score\":0,\"completion_day_level\":{},\"stars\":0},\"1573427\":{\"name\":\"Jonathan Melcher\",\"local_score\":0,\"completion_day_level\":{},\"stars\":0,\"global_score\":0,\"last_star_ts\":0,\"id\":1573427},\"174641\":{\"local_score\":0,\"name\":\"dubyte\",\"id\":174641,\"last_star_ts\":0,\"global_score\":0,\"completion_day_level\":{},\"stars\":0},\"195615\":{\"name\":\"Daniel Shanker\",\"local_score\":996,\"global_score\":0,\"completion_day_level\":{\"5\":{\"2\":{\"get_star_ts\":1701805538,\"star_index\":1193565},\"1\":{\"star_index\":1145746,\"get_star_ts\":1701787654}},\"4\":{\"2\":{\"star_index\":978707,\"get_star_ts\":1701717710},\"1\":{\"star_index\":971657,\"get_star_ts\":1701715681}},\"1\":{\"1\":{\"star_index\":109111,\"get_star_ts\":1701436467},\"2\":{\"get_star_ts\":1701440421,\"star_index\":123833}},\"9\":{\"1\":{\"star_index\":1969266,\"get_star_ts\":1702157023},\"2\":{\"star_index\":1970008,\"get_star_ts\":1702157430}},\"2\":{\"2\":{\"star_index\":388408,\"get_star_ts\":1701523019},\"1\":{\"star_index\":387334,\"get_star_ts\":1701522725}},\"10\":{\"1\":{\"star_index\":2056068,\"get_star_ts\":1702217603},\"2\":{\"get_star_ts\":1702229220,\"star_index\":2079186}},\"11\":{\"1\":{\"get_star_ts\":1702307133,\"star_index\":2212983},\"2\":{\"get_star_ts\":1702308214,\"star_index\":2214991}},\"6\":{\"1\":{\"star_index\":1409732,\"get_star_ts\":1701886826},\"2\":{\"get_star_ts\":1701887088,\"star_index\":1410371}},\"3\":{\"2\":{\"get_star_ts\":1701982640,\"star_index\":1616146},\"1\":{\"star_index\":1606830,\"get_star_ts\":1701978724}},\"8\":{\"2\":{\"star_index\":1750288,\"get_star_ts\":1702047171},\"1\":{\"get_star_ts\":1702044808,\"star_index\":1744382}},\"7\":{\"2\":{\"get_star_ts\":1701962003,\"star_index\":1568620},\"1\":{\"star_index\":1560050,\"get_star_ts\":1701958431}}},\"stars\":22,\"id\":195615,\"last_star_ts\":1702308214},\"123142\":{\"id\":123142,\"last_star_ts\":0,\"global_score\":0,\"stars\":0,\"completion_day_level\":{},\"local_score\":0,\"name\":\"pingpongkingkong\"},\"122937\":{\"stars\":0,\"completion_day_level\":{},\"global_score\":0,\"last_star_ts\":0,\"id\":122937,\"name\":\"iamjackg\",\"local_score\":0},\"996154\":{\"global_score\":0,\"completion_day_level\":{},\"stars\":0,\"last_star_ts\":0,\"id\":996154,\"name\":\"dracoyunho\",\"local_score\":0},\"387136\":{\"last_star_ts\":0,\"id\":387136,\"global_score\":0,\"completion_day_level\":{},\"stars\":0,\"local_score\":0,\"name\":\"Thomas Fix\"},\"543413\":{\"global_score\":0,\"stars\":0,\"completion_day_level\":{},\"last_star_ts\":0,\"id\":543413,\"name\":\"kelly ma\",\"local_score\":0},\"117917\":{\"completion_day_level\":{},\"stars\":0,\"global_score\":0,\"id\":117917,\"last_star_ts\":0,\"name\":\"adambysice\",\"local_score\":0},\"1680356\":{\"local_score\":0,\"name\":\"cameronouellette\",\"id\":1680356,\"last_star_ts\":0,\"global_score\":0,\"completion_day_level\":{},\"stars\":0},\"196695\":{\"completion_day_level\":{},\"stars\":0,\"global_score\":0,\"last_star_ts\":0,\"id\":196695,\"name\":\"Chuck Walker\",\"local_score\":0},\"1795318\":{\"name\":\"Derek Boucher\",\"local_score\":207,\"stars\":5,\"completion_day_level\":{\"2\":{\"2\":{\"get_star_ts\":1701744251,\"star_index\":1042949},\"1\":{\"star_index\":744142,\"get_star_ts\":1701645886}},\"1\":{\"1\":{\"get_star_ts\":1701621162,\"star_index\":666016},\"2\":{\"get_star_ts\":1701639301,\"star_index\":726248}},\"3\":{\"1\":{\"star_index\":1051338,\"get_star_ts\":1701751846}}},\"global_score\":0,\"last_star_ts\":1701751846,\"id\":1795318},\"294290\":{\"id\":294290,\"last_star_ts\":0,\"completion_day_level\":{},\"stars\":0,\"global_score\":0,\"local_score\":0,\"name\":\"ellen-h\"},\"388499\":{\"last_star_ts\":0,\"id\":388499,\"global_score\":0,\"completion_day_level\":{},\"stars\":0,\"local_score\":0,\"name\":\"Marc-François Cochaux-Laberge\"},\"796818\":{\"local_score\":0,\"name\":\"Mark Armstrong\",\"id\":796818,\"last_star_ts\":0,\"global_score\":0,\"completion_day_level\":{},\"stars\":0},\"118311\":{\"completion_day_level\":{},\"stars\":0,\"global_score\":0,\"last_star_ts\":0,\"id\":118311,\"name\":\"Noah Kipin\",\"local_score\":0},\"195610\":{\"local_score\":0,\"name\":\"eolalde\",\"last_star_ts\":0,\"id\":195610,\"completion_day_level\":{},\"stars\":0,\"global_score\":0},\"118426\":{\"local_score\":0,\"name\":\"James Bannerman\",\"id\":118426,\"last_star_ts\":0,\"global_score\":0,\"completion_day_level\":{},\"stars\":0},\"200293\":{\"last_star_ts\":0,\"id\":200293,\"global_score\":0,\"completion_day_level\":{},\"stars\":0,\"local_score\":0,\"name\":\"Stefan Tihanyi\"},\"186094\":{\"global_score\":0,\"stars\":18,\"completion_day_level\":{\"8\":{\"2\":{\"star_index\":1753851,\"get_star_ts\":1702048667},\"1\":{\"get_star_ts\":1702015514,\"star_index\":1671961}},\"7\":{\"1\":{\"star_index\":1491421,\"get_star_ts\":1701930558},\"2\":{\"get_star_ts\":1701931251,\"star_index\":1493558}},\"3\":{\"2\":{\"get_star_ts\":1701649729,\"star_index\":751180},\"1\":{\"get_star_ts\":1701648163,\"star_index\":748661}},\"6\":{\"2\":{\"star_index\":1463926,\"get_star_ts\":1701915059},\"1\":{\"star_index\":1463744,\"get_star_ts\":1701914868}},\"2\":{\"1\":{\"get_star_ts\":1701641796,\"star_index\":733825},\"2\":{\"star_index\":739226,\"get_star_ts\":1701643740}},\"1\":{\"1\":{\"get_star_ts\":1701566958,\"star_index\":520227},\"2\":{\"star_index\":539163,\"get_star_ts\":1701581263}},\"9\":{\"2\":{\"get_star_ts\":1702139664,\"star_index\":1935095},\"1\":{\"star_index\":1934531,\"get_star_ts\":1702139402}},\"5\":{\"1\":{\"get_star_ts\":1701757629,\"star_index\":1069155},\"2\":{\"star_index\":1282496,\"get_star_ts\":1701843360}},\"4\":{\"1\":{\"star_index\":794845,\"get_star_ts\":1701669177},\"2\":{\"get_star_ts\":1701670228,\"star_index\":799527}}},\"id\":186094,\"last_star_ts\":1702139664,\"name\":\"Ricky Kwan\",\"local_score\":807},\"1965603\":{\"name\":\"Tony Secondo\",\"local_score\":0,\"global_score\":0,\"completion_day_level\":{},\"stars\":0,\"last_star_ts\":0,\"id\":1965603},\"118609\":{\"local_score\":1124,\"name\":\"Michael Tardibuono\",\"last_star_ts\":1702358648,\"id\":118609,\"global_score\":47,\"completion_day_level\":{\"4\":{\"2\":{\"get_star_ts\":1701666944,\"star_index\":778441},\"1\":{\"get_star_ts\":1701666549,\"star_index\":773825}},\"12\":{\"1\":{\"get_star_ts\":1702358648,\"star_index\":2284199}},\"5\":{\"2\":{\"star_index\":1471115,\"get_star_ts\":1701923529},\"1\":{\"star_index\":1059396,\"get_star_ts\":1701754884}},\"1\":{\"2\":{\"get_star_ts\":1701407156,\"star_index\":1730},\"1\":{\"star_index\":99,\"get_star_ts\":1701407024}},\"9\":{\"1\":{\"star_index\":1844567,\"get_star_ts\":1702101009},\"2\":{\"star_index\":1846073,\"get_star_ts\":1702101468}},\"10\":{\"1\":{\"star_index\":2047281,\"get_star_ts\":1702212995},\"2\":{\"star_index\":2053056,\"get_star_ts\":1702216023}},\"2\":{\"2\":{\"get_star_ts\":1701493911,\"star_index\":270366},\"1\":{\"star_index\":269129,\"get_star_ts\":1701493769}},\"3\":{\"2\":{\"star_index\":536337,\"get_star_ts\":1701580670},\"1\":{\"get_star_ts\":1701580341,\"star_index\":535171}},\"8\":{\"1\":{\"star_index\":1656276,\"get_star_ts\":1702012287},\"2\":{\"star_index\":1669731,\"get_star_ts\":1702014842}},\"7\":{\"1\":{\"star_index\":1486200,\"get_star_ts\":1701929059},\"2\":{\"star_index\":1492487,\"get_star_ts\":1701930895}},\"11\":{\"2\":{\"get_star_ts\":1702273080,\"star_index\":2143947},\"1\":{\"get_star_ts\":1702271971,\"star_index\":2138337}},\"6\":{\"2\":{\"star_index\":1254216,\"get_star_ts\":1701839199},\"1\":{\"get_star_ts\":1701839123,\"star_index\":1253507}}},\"stars\":23},\"155047\":{\"name\":\"Ivan Peng\",\"local_score\":0,\"global_score\":0,\"stars\":0,\"completion_day_level\":{},\"last_star_ts\":0,\"id\":155047},\"1250840\":{\"name\":\"Cameron Ouellette\",\"local_score\":0,\"completion_day_level\":{},\"stars\":0,\"global_score\":0,\"last_star_ts\":0,\"id\":1250840},\"118852\":{\"name\":\"Michel ElNacouzi\",\"local_score\":0,\"global_score\":0,\"stars\":0,\"completion_day_level\":{},\"id\":118852,\"last_star_ts\":0},\"170332\":{\"local_score\":0,\"name\":null,\"last_star_ts\":0,\"id\":170332,\"global_score\":0,\"completion_day_level\":{},\"stars\":0},\"1650861\":{\"local_score\":0,\"name\":\"Mike Burns\",\"last_star_ts\":0,\"id\":1650861,\"global_score\":0,\"stars\":0,\"completion_day_level\":{}},\"186091\":{\"local_score\":0,\"name\":\"Adam Harvie\",\"last_star_ts\":0,\"id\":186091,\"completion_day_level\":{},\"stars\":0,\"global_score\":0},\"1101759\":{\"completion_day_level\":{},\"stars\":0,\"global_score\":0,\"id\":1101759,\"last_star_ts\":0,\"name\":null,\"local_score\":0},\"1661893\":{\"name\":\"li-ran\",\"local_score\":0,\"global_score\":0,\"stars\":0,\"completion_day_level\":{},\"last_star_ts\":0,\"id\":1661893},\"641575\":{\"id\":641575,\"last_star_ts\":1702359234,\"stars\":24,\"completion_day_level\":{\"6\":{\"1\":{\"get_star_ts\":1701839215,\"star_index\":1254412},\"2\":{\"star_index\":1257731,\"get_star_ts\":1701839466}},\"11\":{\"2\":{\"get_star_ts\":1702271985,\"star_index\":2138429},\"1\":{\"star_index\":2136793,\"get_star_ts\":1702271652}},\"8\":{\"2\":{\"star_index\":1660658,\"get_star_ts\":1702012898},\"1\":{\"get_star_ts\":1702011913,\"star_index\":1653509}},\"7\":{\"2\":{\"star_index\":1481720,\"get_star_ts\":1701928012},\"1\":{\"star_index\":1479091,\"get_star_ts\":1701927445}},\"3\":{\"2\":{\"star_index\":540564,\"get_star_ts\":1701581544},\"1\":{\"get_star_ts\":1701580363,\"star_index\":535241}},\"10\":{\"2\":{\"get_star_ts\":1702238863,\"star_index\":2097961},\"1\":{\"get_star_ts\":1702236269,\"star_index\":2092845}},\"2\":{\"2\":{\"get_star_ts\":1701493809,\"star_index\":269458},\"1\":{\"star_index\":267784,\"get_star_ts\":1701493563}},\"9\":{\"2\":{\"get_star_ts\":1702099063,\"star_index\":1833803},\"1\":{\"star_index\":1832497,\"get_star_ts\":1702098909}},\"1\":{\"1\":{\"get_star_ts\":1701407015,\"star_index\":0},\"2\":{\"get_star_ts\":1701408276,\"star_index\":10574}},\"12\":{\"2\":{\"star_index\":2285828,\"get_star_ts\":1702359234},\"1\":{\"star_index\":2283534,\"get_star_ts\":1702358331}},\"5\":{\"1\":{\"get_star_ts\":1701754130,\"star_index\":1056252},\"2\":{\"get_star_ts\":1701755519,\"star_index\":1061998}},\"4\":{\"2\":{\"get_star_ts\":1701667560,\"star_index\":784410},\"1\":{\"get_star_ts\":1701666305,\"star_index\":771418}}},\"global_score\":0,\"local_score\":1185,\"name\":\"Marie Payne\"},\"635231\":{\"local_score\":0,\"name\":\"aydanjiwani\",\"last_star_ts\":0,\"id\":635231,\"stars\":0,\"completion_day_level\":{},\"global_score\":0},\"659809\":{\"name\":\"grossjo\",\"local_score\":0,\"global_score\":0,\"completion_day_level\":{},\"stars\":0,\"last_star_ts\":0,\"id\":659809},\"986209\":{\"id\":986209,\"last_star_ts\":0,\"global_score\":0,\"completion_day_level\":{},\"stars\":0,\"local_score\":0,\"name\":null},\"10318\":{\"stars\":22,\"completion_day_level\":{\"6\":{\"1\":{\"get_star_ts\":1702153107,\"star_index\":1961612},\"2\":{\"star_index\":1962554,\"get_star_ts\":1702153586}},\"11\":{\"1\":{\"star_index\":2264075,\"get_star_ts\":1702334714},\"2\":{\"star_index\":2264983,\"get_star_ts\":1702335337}},\"7\":{\"2\":{\"get_star_ts\":1702158021,\"star_index\":1971093},\"1\":{\"star_index\":1966183,\"get_star_ts\":1702155407}},\"8\":{\"1\":{\"star_index\":1989011,\"get_star_ts\":1702171200},\"2\":{\"get_star_ts\":1702174313,\"star_index\":1991378}},\"3\":{\"2\":{\"get_star_ts\":1701581464,\"star_index\":540200},\"1\":{\"star_index\":536861,\"get_star_ts\":1701580795}},\"10\":{\"1\":{\"star_index\":2095287,\"get_star_ts\":1702237527},\"2\":{\"get_star_ts\":1702255686,\"star_index\":2124431}},\"2\":{\"1\":{\"get_star_ts\":1701494248,\"star_index\":273781},\"2\":{\"get_star_ts\":1701494626,\"star_index\":277425}},\"1\":{\"1\":{\"get_star_ts\":1701458897,\"star_index\":194296},\"2\":{\"get_star_ts\":1701460038,\"star_index\":198151}},\"9\":{\"2\":{\"star_index\":1992058,\"get_star_ts\":1702175247},\"1\":{\"get_star_ts\":1702175059,\"star_index\":1991910}},\"4\":{\"1\":{\"star_index\":1036239,\"get_star_ts\":1701738708},\"2\":{\"star_index\":1037301,\"get_star_ts\":1701739572}},\"5\":{\"2\":{\"star_index\":1993225,\"get_star_ts\":1702176971},\"1\":{\"star_index\":1241250,\"get_star_ts\":1701827688}}},\"global_score\":0,\"last_star_ts\":1702335337,\"id\":10318,\"name\":\"Pat Smuk\",\"local_score\":976},\"990214\":{\"last_star_ts\":0,\"id\":990214,\"stars\":0,\"completion_day_level\":{},\"global_score\":0,\"local_score\":0,\"name\":\"emmawilsonix\"},\"120850\":{\"local_score\":39,\"name\":\"Sannie Loi\",\"id\":120850,\"last_star_ts\":1702276623,\"completion_day_level\":{\"1\":{\"1\":{\"star_index\":2154286,\"get_star_ts\":1702276623}}},\"stars\":1,\"global_score\":0},\"118403\":{\"local_score\":245,\"name\":\"Peter Chau\",\"last_star_ts\":1701892108,\"id\":118403,\"completion_day_level\":{\"2\":{\"2\":{\"get_star_ts\":1701889366,\"star_index\":1415963},\"1\":{\"star_index\":1415149,\"get_star_ts\":1701889044}},\"1\":{\"2\":{\"star_index\":1412664,\"get_star_ts\":1701888029},\"1\":{\"get_star_ts\":1701887093,\"star_index\":1410386}},\"3\":{\"2\":{\"get_star_ts\":1701892108,\"star_index\":1422906},\"1\":{\"get_star_ts\":1701891662,\"star_index\":1421778}}},\"stars\":6,\"global_score\":0}},\"owner_id\":30841,\"event\":\"2023\"}";
		JSONObject obj = new JSONObject(jsonString);

		
		JSONObject members = obj.getJSONObject("members");
		
		Iterator<String> keys = members.keys();
		
		while(keys.hasNext()) {
			String key = keys.next();
			
			//System.out.println(key);
			JSONObject member = members.getJSONObject(key);
			
			//System.out.println(member);
			
			String name = member.get("name").toString();
			
			
			JSONObject completion = member.getJSONObject("completion_day_level");
			
			Iterator<String> dayKeys = completion.keys();
			
			while(dayKeys.hasNext()) {
				String daykey = dayKeys.next();
				
				if(daykey.trim().equals(INPUT_DAY_KEY) == false) {
					continue;
				}
				if(name.equals("null")) {
					System.out.println(name + "(VP)");
				} else {
					System.out.println(name);
				}
				System.out.println("daykey: " + daykey);
				
				JSONObject parts = completion.getJSONObject(daykey);
				
				Iterator<String> partKeys = parts.keys();
				
				long times[] = new long[2];
				while(partKeys.hasNext()) {
					String partKey = partKeys.next();
					
					
					//System.out.println("partKey: " + partKey);
					//System.out.println(parts.get(partKey).toString());
					
					times[Integer.parseInt(partKey) - 1] = pint(parts.get(partKey).toString().split(":")[2].split("}")[0]);
					//System.out.println(parts.get(partKey).toString().split("\"")[3]);
					//System.out.println();
				}
				
				//A quick brown fox jumps over the lazy dog.
				
				for(int i=0; i<times.length; i++) {
					
					if(times[i] > 0) {
						
						System.out.println("Part " + (i+1) + ":");
						//System.out.println(times[i]);
						java.util.Date time=new java.util.Date((long)times[i]*1000);
						System.out.println(time.toString());
					}
				}
				
				System.out.println();
			}
			
			
			//System.exit(1);
			
			/*
			 * "members": {
    "30841": {
      "name": "bewuethr",
      "completion_day_level": {
        "1": {
          "1": {
            "get_star_ts": "1606840128"
          },
          "2": {
            "get_star_ts": "1606840344"
          }
        },
        "2": {
          "1": {
            "get_star_ts": "1606934299"
          },
          "2": {
            "get_star_ts": "1606934839"
          }
        }
      },
      "stars": 4,
      "last_star_ts": "1606934839",
      "local_score": 54,
      "global_score": 0,
      "id": "30841"
    },
    
			 */
			
		}
		
		//members.getEnum(clazz, key)
		 /*
		    for (int i = 0; i < n; ++i) {
		      final JSONObject person = geodata.getJSONObject(i);
		      System.out.println(person.getInt("id"));
		      System.out.println(person.getString("name"));
		      System.out.println(person.getString("gender"));
		      System.out.println(person.getDouble("latitude"));
		      System.out.println(person.getDouble("longitude"));
		    }*/
		    
	/*
		JSONArray arr = obj.getJSONArray("posts");
		for (int i = 0; i < arr.length(); i++)
		{
		    String post_id = arr.getJSONObject(i).getString("post_id");
		    ......
		}*/
	}
	

	public static int pint(String s) {
		if (IsNumber.isNumber(s)) {
			return Integer.parseInt(s);
		} else {
			System.out.println("Error: (" + s + " is not a number");
			return -1;
		}
	}
}
