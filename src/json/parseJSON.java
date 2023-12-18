package json;

import java.util.Iterator;

import org.json.*;
// https://stackoverflow.com/questions/3280353/how-to-import-a-jar-in-eclipse

import number.IsNumber;

//Just downloaded jar...
//https://github.com/stleary/JSON-java

public class parseJSON {

	public static void main(String args[]) {
		
		String INPUT_DAY_KEY = "17";

		String jsonString = "{\"members\":{\"186091\":{\"completion_day_level\":{},\"stars\":0,\"local_score\":0,\"last_star_ts\":0,\"name\":\"Adam Harvie\",\"id\":186091,\"global_score\":0},\"1573427\":{\"global_score\":0,\"name\":\"Jonathan Melcher\",\"last_star_ts\":0,\"local_score\":0,\"id\":1573427,\"stars\":0,\"completion_day_level\":{}},\"294290\":{\"local_score\":0,\"last_star_ts\":0,\"name\":\"ellen-h\",\"id\":294290,\"global_score\":0,\"completion_day_level\":{},\"stars\":0},\"387136\":{\"global_score\":0,\"local_score\":0,\"last_star_ts\":0,\"name\":\"Thomas Fix\",\"id\":387136,\"stars\":0,\"completion_day_level\":{}},\"1250840\":{\"local_score\":0,\"last_star_ts\":0,\"id\":1250840,\"name\":\"Cameron Ouellette\",\"global_score\":0,\"completion_day_level\":{},\"stars\":0},\"137785\":{\"local_score\":0,\"last_star_ts\":0,\"name\":\"vincentr21\",\"id\":137785,\"global_score\":0,\"completion_day_level\":{},\"stars\":0},\"195610\":{\"global_score\":0,\"local_score\":0,\"last_star_ts\":0,\"name\":\"eolalde\",\"id\":195610,\"stars\":0,\"completion_day_level\":{}},\"118609\":{\"stars\":34,\"completion_day_level\":{\"9\":{\"2\":{\"get_star_ts\":1702101468,\"star_index\":1846073},\"1\":{\"star_index\":1844567,\"get_star_ts\":1702101009}},\"16\":{\"1\":{\"star_index\":2693872,\"get_star_ts\":1702704175},\"2\":{\"star_index\":2695444,\"get_star_ts\":1702704709}},\"13\":{\"2\":{\"star_index\":2396337,\"get_star_ts\":1702448399},\"1\":{\"get_star_ts\":1702447596,\"star_index\":2394717}},\"7\":{\"2\":{\"star_index\":1492487,\"get_star_ts\":1701930895},\"1\":{\"get_star_ts\":1701929059,\"star_index\":1486200}},\"12\":{\"2\":{\"get_star_ts\":1702398325,\"star_index\":2338152},\"1\":{\"star_index\":2284199,\"get_star_ts\":1702358648}},\"15\":{\"1\":{\"star_index\":2590592,\"get_star_ts\":1702617028},\"2\":{\"star_index\":2598263,\"get_star_ts\":1702618946}},\"1\":{\"2\":{\"star_index\":1730,\"get_star_ts\":1701407156},\"1\":{\"get_star_ts\":1701407024,\"star_index\":99}},\"3\":{\"2\":{\"get_star_ts\":1701580670,\"star_index\":536337},\"1\":{\"star_index\":535171,\"get_star_ts\":1701580341}},\"6\":{\"1\":{\"star_index\":1253507,\"get_star_ts\":1701839123},\"2\":{\"star_index\":1254216,\"get_star_ts\":1701839199}},\"5\":{\"2\":{\"get_star_ts\":1701923529,\"star_index\":1471115},\"1\":{\"get_star_ts\":1701754884,\"star_index\":1059396}},\"14\":{\"2\":{\"get_star_ts\":1702532828,\"star_index\":2496669},\"1\":{\"star_index\":2488934,\"get_star_ts\":1702530523}},\"11\":{\"2\":{\"star_index\":2143947,\"get_star_ts\":1702273080},\"1\":{\"star_index\":2138337,\"get_star_ts\":1702271971}},\"8\":{\"1\":{\"get_star_ts\":1702012287,\"star_index\":1656276},\"2\":{\"get_star_ts\":1702014842,\"star_index\":1669731}},\"10\":{\"1\":{\"get_star_ts\":1702212995,\"star_index\":2047281},\"2\":{\"get_star_ts\":1702216023,\"star_index\":2053056}},\"17\":{\"2\":{\"star_index\":2788915,\"get_star_ts\":1702794360},\"1\":{\"get_star_ts\":1702791232,\"star_index\":2785756}},\"2\":{\"1\":{\"get_star_ts\":1701493769,\"star_index\":269129},\"2\":{\"get_star_ts\":1701493911,\"star_index\":270366}},\"4\":{\"1\":{\"get_star_ts\":1701666549,\"star_index\":773825},\"2\":{\"star_index\":778441,\"get_star_ts\":1701666944}}},\"global_score\":47,\"last_star_ts\":1702794360,\"local_score\":1664,\"id\":118609,\"name\":\"Michael Tardibuono\"},\"1687990\":{\"completion_day_level\":{},\"stars\":0,\"last_star_ts\":0,\"id\":1687990,\"local_score\":0,\"name\":\"Sam Leitch\",\"global_score\":0},\"118311\":{\"stars\":0,\"completion_day_level\":{},\"global_score\":0,\"last_star_ts\":0,\"local_score\":0,\"id\":118311,\"name\":\"Noah Kipin\"},\"1650861\":{\"global_score\":0,\"last_star_ts\":0,\"name\":\"Mike Burns\",\"local_score\":0,\"id\":1650861,\"stars\":0,\"completion_day_level\":{}},\"10318\":{\"stars\":32,\"completion_day_level\":{\"11\":{\"2\":{\"get_star_ts\":1702335337,\"star_index\":2264983},\"1\":{\"get_star_ts\":1702334714,\"star_index\":2264075}},\"14\":{\"1\":{\"get_star_ts\":1702602784,\"star_index\":2580701},\"2\":{\"star_index\":2581882,\"get_star_ts\":1702605285}},\"5\":{\"2\":{\"star_index\":1993225,\"get_star_ts\":1702176971},\"1\":{\"get_star_ts\":1701827688,\"star_index\":1241250}},\"6\":{\"1\":{\"star_index\":1961612,\"get_star_ts\":1702153107},\"2\":{\"get_star_ts\":1702153586,\"star_index\":1962554}},\"8\":{\"2\":{\"get_star_ts\":1702174313,\"star_index\":1991378},\"1\":{\"star_index\":1989011,\"get_star_ts\":1702171200}},\"10\":{\"1\":{\"star_index\":2095287,\"get_star_ts\":1702237527},\"2\":{\"get_star_ts\":1702255686,\"star_index\":2124431}},\"2\":{\"1\":{\"star_index\":273781,\"get_star_ts\":1701494248},\"2\":{\"get_star_ts\":1701494626,\"star_index\":277425}},\"4\":{\"2\":{\"star_index\":1037301,\"get_star_ts\":1701739572},\"1\":{\"get_star_ts\":1701738708,\"star_index\":1036239}},\"7\":{\"2\":{\"star_index\":1971093,\"get_star_ts\":1702158021},\"1\":{\"star_index\":1966183,\"get_star_ts\":1702155407}},\"13\":{\"2\":{\"star_index\":2459015,\"get_star_ts\":1702495942},\"1\":{\"star_index\":2457973,\"get_star_ts\":1702495084}},\"16\":{\"2\":{\"get_star_ts\":1702750273,\"star_index\":2753576},\"1\":{\"get_star_ts\":1702749809,\"star_index\":2753059}},\"9\":{\"2\":{\"star_index\":1992058,\"get_star_ts\":1702175247},\"1\":{\"star_index\":1991910,\"get_star_ts\":1702175059}},\"15\":{\"1\":{\"star_index\":2665287,\"get_star_ts\":1702664917},\"2\":{\"get_star_ts\":1702666583,\"star_index\":2666879}},\"12\":{\"2\":{\"star_index\":2379575,\"get_star_ts\":1702433287},\"1\":{\"get_star_ts\":1702432566,\"star_index\":2379194}},\"3\":{\"2\":{\"get_star_ts\":1701581464,\"star_index\":540200},\"1\":{\"star_index\":536861,\"get_star_ts\":1701580795}},\"1\":{\"2\":{\"get_star_ts\":1701460038,\"star_index\":198151},\"1\":{\"get_star_ts\":1701458897,\"star_index\":194296}}},\"global_score\":0,\"name\":\"Pat Smuk\",\"last_star_ts\":1702750273,\"local_score\":1426,\"id\":10318},\"417433\":{\"completion_day_level\":{},\"stars\":0,\"id\":417433,\"last_star_ts\":0,\"local_score\":0,\"name\":\"frigley1\",\"global_score\":0},\"1101759\":{\"stars\":0,\"completion_day_level\":{},\"global_score\":0,\"name\":null,\"last_star_ts\":0,\"local_score\":0,\"id\":1101759},\"155047\":{\"global_score\":0,\"last_star_ts\":0,\"name\":\"Ivan Peng\",\"local_score\":0,\"id\":155047,\"stars\":0,\"completion_day_level\":{}},\"123142\":{\"name\":\"pingpongkingkong\",\"last_star_ts\":0,\"local_score\":0,\"id\":123142,\"global_score\":0,\"completion_day_level\":{},\"stars\":0},\"116481\":{\"stars\":32,\"completion_day_level\":{\"1\":{\"2\":{\"star_index\":109881,\"get_star_ts\":1701436694},\"1\":{\"star_index\":106720,\"get_star_ts\":1701435754}},\"3\":{\"2\":{\"star_index\":677538,\"get_star_ts\":1701624589},\"1\":{\"get_star_ts\":1701624315,\"star_index\":676554}},\"13\":{\"2\":{\"get_star_ts\":1702449052,\"star_index\":2397528},\"1\":{\"star_index\":2395380,\"get_star_ts\":1702447912}},\"7\":{\"1\":{\"get_star_ts\":1701933637,\"star_index\":1499837},\"2\":{\"star_index\":1558409,\"get_star_ts\":1701957728}},\"9\":{\"1\":{\"get_star_ts\":1702100773,\"star_index\":1843732},\"2\":{\"star_index\":1844963,\"get_star_ts\":1702101122}},\"16\":{\"2\":{\"get_star_ts\":1702710860,\"star_index\":2706062},\"1\":{\"star_index\":2705026,\"get_star_ts\":1702709949}},\"15\":{\"1\":{\"get_star_ts\":1702618774,\"star_index\":2597767},\"2\":{\"get_star_ts\":1702621954,\"star_index\":2604252}},\"12\":{\"2\":{\"star_index\":2352740,\"get_star_ts\":1702409258},\"1\":{\"get_star_ts\":1702361496,\"star_index\":2290383}},\"10\":{\"1\":{\"get_star_ts\":1702188635,\"star_index\":2005678},\"2\":{\"get_star_ts\":1702240178,\"star_index\":2100610}},\"2\":{\"2\":{\"get_star_ts\":1701496389,\"star_index\":289486},\"1\":{\"star_index\":286557,\"get_star_ts\":1701495863}},\"4\":{\"2\":{\"get_star_ts\":1701669578,\"star_index\":796863},\"1\":{\"star_index\":790865,\"get_star_ts\":1701668452}},\"11\":{\"2\":{\"star_index\":2152131,\"get_star_ts\":1702275640},\"1\":{\"star_index\":2147476,\"get_star_ts\":1702273987}},\"6\":{\"1\":{\"star_index\":1261527,\"get_star_ts\":1701839734},\"2\":{\"get_star_ts\":1701840025,\"star_index\":1264949}},\"5\":{\"1\":{\"get_star_ts\":1701755663,\"star_index\":1062530},\"2\":{\"star_index\":1251415,\"get_star_ts\":1701837613}},\"14\":{\"2\":{\"star_index\":2550617,\"get_star_ts\":1702572766},\"1\":{\"star_index\":2497612,\"get_star_ts\":1702533230}},\"8\":{\"1\":{\"star_index\":1668518,\"get_star_ts\":1702014521},\"2\":{\"star_index\":1676399,\"get_star_ts\":1702017016}}},\"global_score\":0,\"last_star_ts\":1702710860,\"local_score\":1516,\"id\":116481,\"name\":\"mozesmagyar\"},\"30841\":{\"global_score\":0,\"name\":\"Benjamin Wuethrich\",\"last_star_ts\":1702769001,\"local_score\":1353,\"id\":30841,\"stars\":29,\"completion_day_level\":{\"2\":{\"1\":{\"get_star_ts\":1701496565,\"star_index\":290331},\"2\":{\"get_star_ts\":1701497220,\"star_index\":293258}},\"4\":{\"2\":{\"get_star_ts\":1701668305,\"star_index\":789986},\"1\":{\"star_index\":778403,\"get_star_ts\":1701666941}},\"3\":{\"2\":{\"get_star_ts\":1701586230,\"star_index\":557904},\"1\":{\"get_star_ts\":1701584710,\"star_index\":553138}},\"10\":{\"1\":{\"get_star_ts\":1702190072,\"star_index\":2008419},\"2\":{\"get_star_ts\":1702247689,\"star_index\":2114811}},\"1\":{\"2\":{\"star_index\":153391,\"get_star_ts\":1701447685},\"1\":{\"get_star_ts\":1701411440,\"star_index\":22808}},\"15\":{\"2\":{\"star_index\":2773661,\"get_star_ts\":1702769001},\"1\":{\"get_star_ts\":1702766702,\"star_index\":2771457}},\"8\":{\"2\":{\"star_index\":1671827,\"get_star_ts\":1702015469},\"1\":{\"star_index\":1667713,\"get_star_ts\":1702014322}},\"12\":{\"1\":{\"get_star_ts\":1702360790,\"star_index\":2289158}},\"7\":{\"2\":{\"star_index\":1826036,\"get_star_ts\":1702095011},\"1\":{\"get_star_ts\":1701928957,\"star_index\":1485795}},\"11\":{\"1\":{\"get_star_ts\":1702355613,\"star_index\":2281163},\"2\":{\"star_index\":2281719,\"get_star_ts\":1702356440}},\"13\":{\"2\":{\"get_star_ts\":1702450027,\"star_index\":2399065},\"1\":{\"get_star_ts\":1702446039,\"star_index\":2390832}},\"14\":{\"1\":{\"star_index\":2635684,\"get_star_ts\":1702642182},\"2\":{\"star_index\":2748324,\"get_star_ts\":1702745663}},\"5\":{\"2\":{\"star_index\":1982455,\"get_star_ts\":1702165029},\"1\":{\"star_index\":1061584,\"get_star_ts\":1701755414}},\"6\":{\"2\":{\"star_index\":1260441,\"get_star_ts\":1701839654},\"1\":{\"get_star_ts\":1701839458,\"star_index\":1257620}},\"9\":{\"1\":{\"get_star_ts\":1702098980,\"star_index\":1833112},\"2\":{\"star_index\":1835391,\"get_star_ts\":1702099258}}}},\"990214\":{\"local_score\":0,\"last_star_ts\":0,\"id\":990214,\"name\":\"emmawilsonix\",\"global_score\":0,\"completion_day_level\":{},\"stars\":0},\"197423\":{\"stars\":0,\"completion_day_level\":{},\"global_score\":0,\"last_star_ts\":0,\"name\":null,\"local_score\":0,\"id\":197423},\"118426\":{\"completion_day_level\":{},\"stars\":0,\"local_score\":0,\"last_star_ts\":0,\"id\":118426,\"name\":\"James Bannerman\",\"global_score\":0},\"119856\":{\"global_score\":0,\"local_score\":0,\"last_star_ts\":0,\"name\":\"imnottommy\",\"id\":119856,\"stars\":0,\"completion_day_level\":{}},\"635231\":{\"last_star_ts\":0,\"local_score\":0,\"id\":635231,\"name\":\"aydanjiwani\",\"global_score\":0,\"completion_day_level\":{},\"stars\":0},\"117917\":{\"global_score\":0,\"last_star_ts\":0,\"id\":117917,\"local_score\":0,\"name\":\"adambysice\",\"stars\":0,\"completion_day_level\":{}},\"1965603\":{\"completion_day_level\":{},\"stars\":0,\"local_score\":0,\"last_star_ts\":0,\"name\":\"Tony Secondo\",\"id\":1965603,\"global_score\":0},\"196695\":{\"completion_day_level\":{},\"stars\":0,\"last_star_ts\":0,\"id\":196695,\"local_score\":0,\"name\":\"Chuck Walker\",\"global_score\":0},\"122937\":{\"last_star_ts\":0,\"local_score\":0,\"id\":122937,\"name\":\"iamjackg\",\"global_score\":0,\"completion_day_level\":{},\"stars\":0},\"200293\":{\"completion_day_level\":{},\"stars\":0,\"last_star_ts\":0,\"id\":200293,\"local_score\":0,\"name\":\"Stefan Tihanyi\",\"global_score\":0},\"543413\":{\"global_score\":0,\"last_star_ts\":0,\"id\":543413,\"local_score\":0,\"name\":\"kelly ma\",\"stars\":0,\"completion_day_level\":{}},\"1060563\":{\"completion_day_level\":{},\"stars\":0,\"local_score\":0,\"last_star_ts\":0,\"id\":1060563,\"name\":null,\"global_score\":0},\"1661893\":{\"id\":1661893,\"last_star_ts\":0,\"local_score\":0,\"name\":\"li-ran\",\"global_score\":0,\"completion_day_level\":{},\"stars\":0},\"186094\":{\"stars\":18,\"completion_day_level\":{\"1\":{\"2\":{\"get_star_ts\":1701581263,\"star_index\":539163},\"1\":{\"get_star_ts\":1701566958,\"star_index\":520227}},\"3\":{\"1\":{\"get_star_ts\":1701648163,\"star_index\":748661},\"2\":{\"star_index\":751180,\"get_star_ts\":1701649729}},\"4\":{\"1\":{\"get_star_ts\":1701669177,\"star_index\":794845},\"2\":{\"get_star_ts\":1701670228,\"star_index\":799527}},\"2\":{\"2\":{\"star_index\":739226,\"get_star_ts\":1701643740},\"1\":{\"get_star_ts\":1701641796,\"star_index\":733825}},\"9\":{\"2\":{\"get_star_ts\":1702139664,\"star_index\":1935095},\"1\":{\"star_index\":1934531,\"get_star_ts\":1702139402}},\"6\":{\"1\":{\"get_star_ts\":1701914868,\"star_index\":1463744},\"2\":{\"star_index\":1463926,\"get_star_ts\":1701915059}},\"5\":{\"1\":{\"star_index\":1069155,\"get_star_ts\":1701757629},\"2\":{\"star_index\":1282496,\"get_star_ts\":1701843360}},\"7\":{\"2\":{\"star_index\":1493558,\"get_star_ts\":1701931251},\"1\":{\"get_star_ts\":1701930558,\"star_index\":1491421}},\"8\":{\"2\":{\"star_index\":1753851,\"get_star_ts\":1702048667},\"1\":{\"get_star_ts\":1702015514,\"star_index\":1671961}}},\"global_score\":0,\"local_score\":807,\"last_star_ts\":1702139664,\"name\":\"Ricky Kwan\",\"id\":186094},\"250349\":{\"global_score\":0,\"name\":\"David McFadzean\",\"last_star_ts\":1702741753,\"local_score\":1454,\"id\":250349,\"stars\":32,\"completion_day_level\":{\"12\":{\"2\":{\"star_index\":2689724,\"get_star_ts\":1702696029},\"1\":{\"get_star_ts\":1702393430,\"star_index\":2331242}},\"15\":{\"2\":{\"star_index\":2647337,\"get_star_ts\":1702650636},\"1\":{\"get_star_ts\":1702617420,\"star_index\":2592777}},\"16\":{\"2\":{\"star_index\":2743420,\"get_star_ts\":1702741753},\"1\":{\"get_star_ts\":1702740574,\"star_index\":2741903}},\"9\":{\"1\":{\"get_star_ts\":1702130674,\"star_index\":1914309},\"2\":{\"star_index\":1914961,\"get_star_ts\":1702130954}},\"7\":{\"1\":{\"star_index\":1583485,\"get_star_ts\":1701968138},\"2\":{\"get_star_ts\":1701985838,\"star_index\":1623626}},\"13\":{\"1\":{\"star_index\":2452163,\"get_star_ts\":1702490214},\"2\":{\"get_star_ts\":1702495481,\"star_index\":2458457}},\"3\":{\"1\":{\"star_index\":759087,\"get_star_ts\":1701655810},\"2\":{\"star_index\":760401,\"get_star_ts\":1701656910}},\"1\":{\"1\":{\"get_star_ts\":1701480316,\"star_index\":252054},\"2\":{\"get_star_ts\":1701522787,\"star_index\":387570}},\"8\":{\"2\":{\"get_star_ts\":1702086600,\"star_index\":1820480},\"1\":{\"get_star_ts\":1702044795,\"star_index\":1744345}},\"5\":{\"1\":{\"get_star_ts\":1701791339,\"star_index\":1155763},\"2\":{\"star_index\":1465291,\"get_star_ts\":1701916597}},\"14\":{\"1\":{\"get_star_ts\":1702565081,\"star_index\":2541036},\"2\":{\"star_index\":2562210,\"get_star_ts\":1702583200}},\"6\":{\"2\":{\"get_star_ts\":1701877102,\"star_index\":1384795},\"1\":{\"star_index\":1377163,\"get_star_ts\":1701874312}},\"11\":{\"2\":{\"get_star_ts\":1702313060,\"star_index\":2224293},\"1\":{\"get_star_ts\":1702308784,\"star_index\":2216109}},\"4\":{\"1\":{\"star_index\":908512,\"get_star_ts\":1701698474},\"2\":{\"star_index\":926957,\"get_star_ts\":1701703375}},\"2\":{\"2\":{\"get_star_ts\":1701573466,\"star_index\":527844},\"1\":{\"star_index\":527245,\"get_star_ts\":1701572913}},\"10\":{\"1\":{\"get_star_ts\":1702221098,\"star_index\":2062763},\"2\":{\"get_star_ts\":1702229599,\"star_index\":2079937}}}},\"118403\":{\"stars\":10,\"completion_day_level\":{\"1\":{\"2\":{\"get_star_ts\":1701888029,\"star_index\":1412664},\"1\":{\"get_star_ts\":1701887093,\"star_index\":1410386}},\"5\":{\"2\":{\"star_index\":2470759,\"get_star_ts\":1702504526},\"1\":{\"get_star_ts\":1702503646,\"star_index\":2469546}},\"3\":{\"1\":{\"star_index\":1421778,\"get_star_ts\":1701891662},\"2\":{\"get_star_ts\":1701892108,\"star_index\":1422906}},\"2\":{\"2\":{\"get_star_ts\":1701889366,\"star_index\":1415963},\"1\":{\"get_star_ts\":1701889044,\"star_index\":1415149}},\"4\":{\"1\":{\"get_star_ts\":1702500339,\"star_index\":2464977},\"2\":{\"get_star_ts\":1702501020,\"star_index\":2465998}}},\"global_score\":0,\"local_score\":413,\"last_star_ts\":1702504526,\"id\":118403,\"name\":\"Peter Chau\"},\"388499\":{\"stars\":2,\"completion_day_level\":{\"1\":{\"1\":{\"get_star_ts\":1702651489,\"star_index\":2648536},\"2\":{\"star_index\":2652384,\"get_star_ts\":1702654124}}},\"global_score\":0,\"local_score\":77,\"last_star_ts\":1702654124,\"id\":388499,\"name\":\"Marc-François Cochaux-Laberge\"},\"641575\":{\"last_star_ts\":1702791746,\"local_score\":1683,\"id\":641575,\"name\":\"Marie Payne\",\"global_score\":0,\"completion_day_level\":{\"1\":{\"2\":{\"get_star_ts\":1701408276,\"star_index\":10574},\"1\":{\"star_index\":0,\"get_star_ts\":1701407015}},\"3\":{\"1\":{\"get_star_ts\":1701580363,\"star_index\":535241},\"2\":{\"star_index\":540564,\"get_star_ts\":1701581544}},\"9\":{\"1\":{\"star_index\":1832497,\"get_star_ts\":1702098909},\"2\":{\"star_index\":1833803,\"get_star_ts\":1702099063}},\"16\":{\"2\":{\"get_star_ts\":1702704766,\"star_index\":2695616},\"1\":{\"star_index\":2694454,\"get_star_ts\":1702704373}},\"13\":{\"1\":{\"star_index\":2387693,\"get_star_ts\":1702445013},\"2\":{\"get_star_ts\":1702445562,\"star_index\":2389420}},\"7\":{\"2\":{\"get_star_ts\":1701928012,\"star_index\":1481720},\"1\":{\"get_star_ts\":1701927445,\"star_index\":1479091}},\"12\":{\"1\":{\"star_index\":2283534,\"get_star_ts\":1702358331},\"2\":{\"get_star_ts\":1702359234,\"star_index\":2285828}},\"15\":{\"2\":{\"get_star_ts\":1702617755,\"star_index\":2594184},\"1\":{\"star_index\":2588334,\"get_star_ts\":1702616740}},\"10\":{\"2\":{\"star_index\":2097961,\"get_star_ts\":1702238863},\"1\":{\"get_star_ts\":1702236269,\"star_index\":2092845}},\"17\":{\"2\":{\"star_index\":2786366,\"get_star_ts\":1702791746},\"1\":{\"star_index\":2784845,\"get_star_ts\":1702790648}},\"4\":{\"2\":{\"star_index\":784410,\"get_star_ts\":1701667560},\"1\":{\"get_star_ts\":1701666305,\"star_index\":771418}},\"2\":{\"2\":{\"get_star_ts\":1701493809,\"star_index\":269458},\"1\":{\"get_star_ts\":1701493563,\"star_index\":267784}},\"6\":{\"1\":{\"star_index\":1254412,\"get_star_ts\":1701839215},\"2\":{\"star_index\":1257731,\"get_star_ts\":1701839466}},\"5\":{\"1\":{\"get_star_ts\":1701754130,\"star_index\":1056252},\"2\":{\"get_star_ts\":1701755519,\"star_index\":1061998}},\"14\":{\"2\":{\"get_star_ts\":1702531759,\"star_index\":2493618},\"1\":{\"get_star_ts\":1702530359,\"star_index\":2488322}},\"11\":{\"2\":{\"star_index\":2138429,\"get_star_ts\":1702271985},\"1\":{\"star_index\":2136793,\"get_star_ts\":1702271652}},\"8\":{\"2\":{\"star_index\":1660658,\"get_star_ts\":1702012898},\"1\":{\"get_star_ts\":1702011913,\"star_index\":1653509}}},\"stars\":34},\"1795318\":{\"last_star_ts\":1701751846,\"name\":\"Derek Boucher\",\"local_score\":207,\"id\":1795318,\"global_score\":0,\"completion_day_level\":{\"1\":{\"2\":{\"get_star_ts\":1701639301,\"star_index\":726248},\"1\":{\"get_star_ts\":1701621162,\"star_index\":666016}},\"3\":{\"1\":{\"get_star_ts\":1701751846,\"star_index\":1051338}},\"2\":{\"1\":{\"get_star_ts\":1701645886,\"star_index\":744142},\"2\":{\"star_index\":1042949,\"get_star_ts\":1701744251}}},\"stars\":5},\"995421\":{\"stars\":0,\"completion_day_level\":{},\"global_score\":0,\"name\":\"Quin Millard\",\"last_star_ts\":0,\"local_score\":0,\"id\":995421},\"996154\":{\"completion_day_level\":{},\"stars\":0,\"last_star_ts\":0,\"name\":\"dracoyunho\",\"local_score\":0,\"id\":996154,\"global_score\":0},\"796818\":{\"stars\":0,\"completion_day_level\":{},\"global_score\":0,\"name\":\"Mark Armstrong\",\"last_star_ts\":0,\"local_score\":0,\"id\":796818},\"986209\":{\"completion_day_level\":{},\"stars\":0,\"last_star_ts\":0,\"local_score\":0,\"name\":null,\"id\":986209,\"global_score\":0},\"170332\":{\"stars\":0,\"completion_day_level\":{},\"global_score\":0,\"last_star_ts\":0,\"name\":null,\"local_score\":0,\"id\":170332},\"195615\":{\"last_star_ts\":1702737680,\"local_score\":1454,\"name\":\"Daniel Shanker\",\"id\":195615,\"global_score\":0,\"completion_day_level\":{\"8\":{\"1\":{\"get_star_ts\":1702044808,\"star_index\":1744382},\"2\":{\"get_star_ts\":1702047171,\"star_index\":1750288}},\"11\":{\"2\":{\"get_star_ts\":1702308214,\"star_index\":2214991},\"1\":{\"star_index\":2212983,\"get_star_ts\":1702307133}},\"14\":{\"2\":{\"get_star_ts\":1702649426,\"star_index\":2645657},\"1\":{\"get_star_ts\":1702573293,\"star_index\":2551298}},\"5\":{\"1\":{\"star_index\":1145746,\"get_star_ts\":1701787654},\"2\":{\"get_star_ts\":1701805538,\"star_index\":1193565}},\"6\":{\"1\":{\"star_index\":1409732,\"get_star_ts\":1701886826},\"2\":{\"star_index\":1410371,\"get_star_ts\":1701887088}},\"2\":{\"2\":{\"star_index\":388408,\"get_star_ts\":1701523019},\"1\":{\"star_index\":387334,\"get_star_ts\":1701522725}},\"4\":{\"1\":{\"get_star_ts\":1701715681,\"star_index\":971657},\"2\":{\"get_star_ts\":1701717710,\"star_index\":978707}},\"10\":{\"1\":{\"get_star_ts\":1702217603,\"star_index\":2056068},\"2\":{\"get_star_ts\":1702229220,\"star_index\":2079186}},\"15\":{\"1\":{\"star_index\":2647125,\"get_star_ts\":1702650503},\"2\":{\"get_star_ts\":1702652816,\"star_index\":2650605}},\"12\":{\"1\":{\"get_star_ts\":1702401382,\"star_index\":2342398},\"2\":{\"get_star_ts\":1702412814,\"star_index\":2357944}},\"7\":{\"2\":{\"get_star_ts\":1701962003,\"star_index\":1568620},\"1\":{\"star_index\":1560050,\"get_star_ts\":1701958431}},\"13\":{\"1\":{\"get_star_ts\":1702493625,\"star_index\":2456265},\"2\":{\"get_star_ts\":1702569778,\"star_index\":2547083}},\"16\":{\"2\":{\"star_index\":2738391,\"get_star_ts\":1702737680},\"1\":{\"star_index\":2737516,\"get_star_ts\":1702736978}},\"9\":{\"1\":{\"star_index\":1969266,\"get_star_ts\":1702157023},\"2\":{\"star_index\":1970008,\"get_star_ts\":1702157430}},\"3\":{\"2\":{\"get_star_ts\":1701982640,\"star_index\":1616146},\"1\":{\"star_index\":1606830,\"get_star_ts\":1701978724}},\"1\":{\"2\":{\"star_index\":123833,\"get_star_ts\":1701440421},\"1\":{\"star_index\":109111,\"get_star_ts\":1701436467}}},\"stars\":32},\"195611\":{\"completion_day_level\":{\"2\":{\"1\":{\"get_star_ts\":1701579403,\"star_index\":533789},\"2\":{\"star_index\":711825,\"get_star_ts\":1701635002}},\"3\":{\"1\":{\"get_star_ts\":1701923702,\"star_index\":1471247}},\"1\":{\"2\":{\"star_index\":224439,\"get_star_ts\":1701467952},\"1\":{\"star_index\":156247,\"get_star_ts\":1701448405}}},\"stars\":5,\"last_star_ts\":1701923702,\"name\":\"Dumtard\",\"local_score\":216,\"id\":195611,\"global_score\":0},\"118852\":{\"id\":118852,\"last_star_ts\":0,\"local_score\":0,\"name\":\"Michel ElNacouzi\",\"global_score\":0,\"completion_day_level\":{},\"stars\":0},\"1680356\":{\"stars\":0,\"completion_day_level\":{},\"global_score\":0,\"id\":1680356,\"last_star_ts\":0,\"local_score\":0,\"name\":\"cameronouellette\"},\"374494\":{\"completion_day_level\":{},\"stars\":0,\"last_star_ts\":0,\"local_score\":0,\"id\":374494,\"name\":null,\"global_score\":0},\"659809\":{\"global_score\":0,\"local_score\":0,\"last_star_ts\":0,\"id\":659809,\"name\":\"grossjo\",\"stars\":0,\"completion_day_level\":{}},\"174641\":{\"completion_day_level\":{},\"stars\":0,\"last_star_ts\":0,\"local_score\":0,\"name\":\"dubyte\",\"id\":174641,\"global_score\":0},\"120850\":{\"completion_day_level\":{\"1\":{\"1\":{\"get_star_ts\":1702276623,\"star_index\":2154286}}},\"stars\":1,\"last_star_ts\":1702276623,\"local_score\":39,\"id\":120850,\"name\":\"Sannie Loi\",\"global_score\":0}},\"owner_id\":30841,\"event\":\"2023\"}";
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
