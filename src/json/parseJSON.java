package json;

import java.util.Iterator;

import org.json.*;
//import javax.json;
// https://stackoverflow.com/questions/3280353/how-to-import-a-jar-in-eclipse

import number.IsNumber;

//Just downloaded jar...
//https://github.com/stleary/JSON-java

public class parseJSON {

	public static void main(String args[]) {
		
		String INPUT_DAY_KEY = "18";

		String jsonString = "{\"owner_id\":30841,\"event\":\"2023\",\"members\":{\"1687990\":{\"name\":\"Sam Leitch\",\"local_score\":0,\"stars\":0,\"completion_day_level\":{},\"id\":1687990,\"last_star_ts\":0,\"global_score\":0},\"118403\":{\"last_star_ts\":1702504526,\"global_score\":0,\"completion_day_level\":{\"5\":{\"1\":{\"star_index\":2469546,\"get_star_ts\":1702503646},\"2\":{\"star_index\":2470759,\"get_star_ts\":1702504526}},\"4\":{\"2\":{\"get_star_ts\":1702501020,\"star_index\":2465998},\"1\":{\"get_star_ts\":1702500339,\"star_index\":2464977}},\"1\":{\"1\":{\"get_star_ts\":1701887093,\"star_index\":1410386},\"2\":{\"get_star_ts\":1701888029,\"star_index\":1412664}},\"2\":{\"1\":{\"get_star_ts\":1701889044,\"star_index\":1415149},\"2\":{\"get_star_ts\":1701889366,\"star_index\":1415963}},\"3\":{\"2\":{\"star_index\":1422906,\"get_star_ts\":1701892108},\"1\":{\"star_index\":1421778,\"get_star_ts\":1701891662}}},\"id\":118403,\"stars\":10,\"local_score\":413,\"name\":\"Peter Chau\"},\"796818\":{\"stars\":0,\"name\":\"Mark Armstrong\",\"local_score\":0,\"last_star_ts\":0,\"global_score\":0,\"completion_day_level\":{},\"id\":796818},\"118426\":{\"completion_day_level\":{},\"id\":118426,\"global_score\":0,\"last_star_ts\":0,\"name\":\"James Bannerman\",\"local_score\":0,\"stars\":0},\"195611\":{\"last_star_ts\":1701923702,\"global_score\":0,\"completion_day_level\":{\"3\":{\"1\":{\"get_star_ts\":1701923702,\"star_index\":1471247}},\"2\":{\"2\":{\"star_index\":711825,\"get_star_ts\":1701635002},\"1\":{\"get_star_ts\":1701579403,\"star_index\":533789}},\"1\":{\"2\":{\"star_index\":224439,\"get_star_ts\":1701467952},\"1\":{\"star_index\":156247,\"get_star_ts\":1701448405}}},\"id\":195611,\"stars\":5,\"name\":\"Dumtard\",\"local_score\":216},\"122937\":{\"stars\":0,\"local_score\":0,\"name\":\"iamjackg\",\"global_score\":0,\"last_star_ts\":0,\"completion_day_level\":{},\"id\":122937},\"1250840\":{\"stars\":0,\"local_score\":0,\"name\":\"Cameron Ouellette\",\"last_star_ts\":0,\"global_score\":0,\"id\":1250840,\"completion_day_level\":{}},\"119856\":{\"local_score\":0,\"name\":\"imnottommy\",\"stars\":0,\"id\":119856,\"completion_day_level\":{},\"last_star_ts\":0,\"global_score\":0},\"120850\":{\"stars\":1,\"name\":\"Sannie Loi\",\"local_score\":39,\"last_star_ts\":1702276623,\"global_score\":0,\"id\":120850,\"completion_day_level\":{\"1\":{\"1\":{\"star_index\":2154286,\"get_star_ts\":1702276623}}}},\"659809\":{\"completion_day_level\":{},\"id\":659809,\"last_star_ts\":0,\"global_score\":0,\"local_score\":0,\"name\":\"grossjo\",\"stars\":0},\"1060563\":{\"global_score\":0,\"last_star_ts\":0,\"id\":1060563,\"completion_day_level\":{},\"stars\":0,\"local_score\":0,\"name\":null},\"1101759\":{\"last_star_ts\":0,\"global_score\":0,\"id\":1101759,\"completion_day_level\":{},\"stars\":0,\"name\":null,\"local_score\":0},\"116481\":{\"completion_day_level\":{\"12\":{\"1\":{\"get_star_ts\":1702361496,\"star_index\":2290383},\"2\":{\"get_star_ts\":1702409258,\"star_index\":2352740}},\"14\":{\"2\":{\"star_index\":2550617,\"get_star_ts\":1702572766},\"1\":{\"star_index\":2497612,\"get_star_ts\":1702533230}},\"1\":{\"2\":{\"star_index\":109881,\"get_star_ts\":1701436694},\"1\":{\"get_star_ts\":1701435754,\"star_index\":106720}},\"16\":{\"1\":{\"star_index\":2705026,\"get_star_ts\":1702709949},\"2\":{\"star_index\":2706062,\"get_star_ts\":1702710860}},\"13\":{\"2\":{\"star_index\":2397528,\"get_star_ts\":1702449052},\"1\":{\"get_star_ts\":1702447912,\"star_index\":2395380}},\"6\":{\"2\":{\"star_index\":1264949,\"get_star_ts\":1701840025},\"1\":{\"get_star_ts\":1701839734,\"star_index\":1261527}},\"7\":{\"2\":{\"get_star_ts\":1701957728,\"star_index\":1558409},\"1\":{\"star_index\":1499837,\"get_star_ts\":1701933637}},\"5\":{\"2\":{\"star_index\":1251415,\"get_star_ts\":1701837613},\"1\":{\"get_star_ts\":1701755663,\"star_index\":1062530}},\"10\":{\"2\":{\"get_star_ts\":1702240178,\"star_index\":2100610},\"1\":{\"star_index\":2005678,\"get_star_ts\":1702188635}},\"8\":{\"2\":{\"star_index\":1676399,\"get_star_ts\":1702017016},\"1\":{\"star_index\":1668518,\"get_star_ts\":1702014521}},\"17\":{\"2\":{\"star_index\":2848255,\"get_star_ts\":1702855981},\"1\":{\"get_star_ts\":1702822816,\"star_index\":2813438}},\"18\":{\"2\":{\"star_index\":2896503,\"get_star_ts\":1702910888},\"1\":{\"star_index\":2890098,\"get_star_ts\":1702904880}},\"4\":{\"2\":{\"get_star_ts\":1701669578,\"star_index\":796863},\"1\":{\"star_index\":790865,\"get_star_ts\":1701668452}},\"15\":{\"1\":{\"get_star_ts\":1702618774,\"star_index\":2597767},\"2\":{\"star_index\":2604252,\"get_star_ts\":1702621954}},\"3\":{\"2\":{\"get_star_ts\":1701624589,\"star_index\":677538},\"1\":{\"get_star_ts\":1701624315,\"star_index\":676554}},\"11\":{\"1\":{\"star_index\":2147476,\"get_star_ts\":1702273987},\"2\":{\"get_star_ts\":1702275640,\"star_index\":2152131}},\"2\":{\"1\":{\"star_index\":286557,\"get_star_ts\":1701495863},\"2\":{\"star_index\":289486,\"get_star_ts\":1701496389}},\"9\":{\"2\":{\"star_index\":1844963,\"get_star_ts\":1702101122},\"1\":{\"star_index\":1843732,\"get_star_ts\":1702100773}}},\"id\":116481,\"last_star_ts\":1702910888,\"global_score\":0,\"name\":\"mozesmagyar\",\"local_score\":1708,\"stars\":36},\"197423\":{\"last_star_ts\":0,\"global_score\":0,\"completion_day_level\":{},\"id\":197423,\"stars\":0,\"local_score\":0,\"name\":null},\"10318\":{\"last_star_ts\":1702843676,\"global_score\":0,\"completion_day_level\":{\"14\":{\"1\":{\"star_index\":2580701,\"get_star_ts\":1702602784},\"2\":{\"star_index\":2581882,\"get_star_ts\":1702605285}},\"12\":{\"2\":{\"star_index\":2379575,\"get_star_ts\":1702433287},\"1\":{\"star_index\":2379194,\"get_star_ts\":1702432566}},\"1\":{\"2\":{\"star_index\":198151,\"get_star_ts\":1701460038},\"1\":{\"star_index\":194296,\"get_star_ts\":1701458897}},\"6\":{\"2\":{\"star_index\":1962554,\"get_star_ts\":1702153586},\"1\":{\"get_star_ts\":1702153107,\"star_index\":1961612}},\"13\":{\"2\":{\"get_star_ts\":1702495942,\"star_index\":2459015},\"1\":{\"star_index\":2457973,\"get_star_ts\":1702495084}},\"16\":{\"1\":{\"get_star_ts\":1702749809,\"star_index\":2753059},\"2\":{\"get_star_ts\":1702750273,\"star_index\":2753576}},\"5\":{\"2\":{\"star_index\":1993225,\"get_star_ts\":1702176971},\"1\":{\"get_star_ts\":1701827688,\"star_index\":1241250}},\"7\":{\"2\":{\"star_index\":1971093,\"get_star_ts\":1702158021},\"1\":{\"star_index\":1966183,\"get_star_ts\":1702155407}},\"10\":{\"2\":{\"star_index\":2124431,\"get_star_ts\":1702255686},\"1\":{\"get_star_ts\":1702237527,\"star_index\":2095287}},\"15\":{\"1\":{\"get_star_ts\":1702664917,\"star_index\":2665287},\"2\":{\"get_star_ts\":1702666583,\"star_index\":2666879}},\"4\":{\"2\":{\"star_index\":1037301,\"get_star_ts\":1701739572},\"1\":{\"get_star_ts\":1701738708,\"star_index\":1036239}},\"8\":{\"2\":{\"star_index\":1991378,\"get_star_ts\":1702174313},\"1\":{\"star_index\":1989011,\"get_star_ts\":1702171200}},\"17\":{\"1\":{\"star_index\":2834108,\"get_star_ts\":1702842271},\"2\":{\"get_star_ts\":1702843676,\"star_index\":2835647}},\"2\":{\"2\":{\"get_star_ts\":1701494626,\"star_index\":277425},\"1\":{\"star_index\":273781,\"get_star_ts\":1701494248}},\"9\":{\"2\":{\"star_index\":1992058,\"get_star_ts\":1702175247},\"1\":{\"get_star_ts\":1702175059,\"star_index\":1991910}},\"11\":{\"1\":{\"star_index\":2264075,\"get_star_ts\":1702334714},\"2\":{\"get_star_ts\":1702335337,\"star_index\":2264983}},\"3\":{\"2\":{\"get_star_ts\":1701581464,\"star_index\":540200},\"1\":{\"get_star_ts\":1701580795,\"star_index\":536861}}},\"id\":10318,\"stars\":34,\"name\":\"Pat Smuk\",\"local_score\":1521},\"1795318\":{\"id\":1795318,\"completion_day_level\":{\"3\":{\"1\":{\"star_index\":1051338,\"get_star_ts\":1701751846}},\"2\":{\"1\":{\"star_index\":744142,\"get_star_ts\":1701645886},\"2\":{\"star_index\":1042949,\"get_star_ts\":1701744251}},\"1\":{\"1\":{\"star_index\":666016,\"get_star_ts\":1701621162},\"2\":{\"star_index\":726248,\"get_star_ts\":1701639301}}},\"global_score\":0,\"last_star_ts\":1701751846,\"name\":\"Derek Boucher\",\"local_score\":207,\"stars\":5},\"30841\":{\"stars\":29,\"name\":\"Benjamin Wuethrich\",\"local_score\":1353,\"last_star_ts\":1702769001,\"global_score\":0,\"id\":30841,\"completion_day_level\":{\"3\":{\"1\":{\"get_star_ts\":1701584710,\"star_index\":553138},\"2\":{\"star_index\":557904,\"get_star_ts\":1701586230}},\"11\":{\"2\":{\"star_index\":2281719,\"get_star_ts\":1702356440},\"1\":{\"get_star_ts\":1702355613,\"star_index\":2281163}},\"9\":{\"1\":{\"star_index\":1833112,\"get_star_ts\":1702098980},\"2\":{\"get_star_ts\":1702099258,\"star_index\":1835391}},\"2\":{\"2\":{\"get_star_ts\":1701497220,\"star_index\":293258},\"1\":{\"get_star_ts\":1701496565,\"star_index\":290331}},\"8\":{\"2\":{\"star_index\":1671827,\"get_star_ts\":1702015469},\"1\":{\"get_star_ts\":1702014322,\"star_index\":1667713}},\"15\":{\"1\":{\"star_index\":2771457,\"get_star_ts\":1702766702},\"2\":{\"get_star_ts\":1702769001,\"star_index\":2773661}},\"6\":{\"1\":{\"star_index\":1257620,\"get_star_ts\":1701839458},\"2\":{\"get_star_ts\":1701839654,\"star_index\":1260441}},\"13\":{\"2\":{\"star_index\":2399065,\"get_star_ts\":1702450027},\"1\":{\"star_index\":2390832,\"get_star_ts\":1702446039}},\"4\":{\"1\":{\"star_index\":778403,\"get_star_ts\":1701666941},\"2\":{\"star_index\":789986,\"get_star_ts\":1701668305}},\"10\":{\"1\":{\"get_star_ts\":1702190072,\"star_index\":2008419},\"2\":{\"get_star_ts\":1702247689,\"star_index\":2114811}},\"1\":{\"2\":{\"get_star_ts\":1701447685,\"star_index\":153391},\"1\":{\"get_star_ts\":1701411440,\"star_index\":22808}},\"7\":{\"1\":{\"star_index\":1485795,\"get_star_ts\":1701928957},\"2\":{\"get_star_ts\":1702095011,\"star_index\":1826036}},\"12\":{\"1\":{\"star_index\":2289158,\"get_star_ts\":1702360790}},\"14\":{\"2\":{\"get_star_ts\":1702745663,\"star_index\":2748324},\"1\":{\"star_index\":2635684,\"get_star_ts\":1702642182}},\"5\":{\"1\":{\"star_index\":1061584,\"get_star_ts\":1701755414},\"2\":{\"star_index\":1982455,\"get_star_ts\":1702165029}}}},\"118852\":{\"name\":\"Michel ElNacouzi\",\"local_score\":0,\"stars\":0,\"completion_day_level\":{},\"id\":118852,\"last_star_ts\":0,\"global_score\":0},\"995421\":{\"global_score\":0,\"last_star_ts\":0,\"id\":995421,\"completion_day_level\":{},\"stars\":0,\"local_score\":0,\"name\":\"Quin Millard\"},\"374494\":{\"id\":374494,\"completion_day_level\":{},\"last_star_ts\":0,\"global_score\":0,\"local_score\":0,\"name\":null,\"stars\":0},\"155047\":{\"local_score\":0,\"name\":\"Ivan Peng\",\"stars\":0,\"id\":155047,\"completion_day_level\":{},\"global_score\":0,\"last_star_ts\":0},\"641575\":{\"last_star_ts\":1702877473,\"global_score\":0,\"completion_day_level\":{\"1\":{\"1\":{\"get_star_ts\":1701407015,\"star_index\":0},\"2\":{\"get_star_ts\":1701408276,\"star_index\":10574}},\"14\":{\"1\":{\"star_index\":2488322,\"get_star_ts\":1702530359},\"2\":{\"get_star_ts\":1702531759,\"star_index\":2493618}},\"12\":{\"2\":{\"star_index\":2285828,\"get_star_ts\":1702359234},\"1\":{\"star_index\":2283534,\"get_star_ts\":1702358331}},\"6\":{\"1\":{\"get_star_ts\":1701839215,\"star_index\":1254412},\"2\":{\"star_index\":1257731,\"get_star_ts\":1701839466}},\"13\":{\"1\":{\"get_star_ts\":1702445013,\"star_index\":2387693},\"2\":{\"star_index\":2389420,\"get_star_ts\":1702445562}},\"16\":{\"1\":{\"star_index\":2694454,\"get_star_ts\":1702704373},\"2\":{\"get_star_ts\":1702704766,\"star_index\":2695616}},\"10\":{\"2\":{\"star_index\":2097961,\"get_star_ts\":1702238863},\"1\":{\"get_star_ts\":1702236269,\"star_index\":2092845}},\"5\":{\"2\":{\"get_star_ts\":1701755519,\"star_index\":1061998},\"1\":{\"star_index\":1056252,\"get_star_ts\":1701754130}},\"7\":{\"1\":{\"star_index\":1479091,\"get_star_ts\":1701927445},\"2\":{\"get_star_ts\":1701928012,\"star_index\":1481720}},\"2\":{\"1\":{\"star_index\":267784,\"get_star_ts\":1701493563},\"2\":{\"get_star_ts\":1701493809,\"star_index\":269458}},\"9\":{\"1\":{\"get_star_ts\":1702098909,\"star_index\":1832497},\"2\":{\"get_star_ts\":1702099063,\"star_index\":1833803}},\"11\":{\"1\":{\"star_index\":2136793,\"get_star_ts\":1702271652},\"2\":{\"star_index\":2138429,\"get_star_ts\":1702271985}},\"3\":{\"2\":{\"star_index\":540564,\"get_star_ts\":1701581544},\"1\":{\"star_index\":535241,\"get_star_ts\":1701580363}},\"15\":{\"1\":{\"star_index\":2588334,\"get_star_ts\":1702616740},\"2\":{\"get_star_ts\":1702617755,\"star_index\":2594184}},\"4\":{\"1\":{\"star_index\":771418,\"get_star_ts\":1701666305},\"2\":{\"star_index\":784410,\"get_star_ts\":1701667560}},\"8\":{\"2\":{\"star_index\":1660658,\"get_star_ts\":1702012898},\"1\":{\"star_index\":1653509,\"get_star_ts\":1702011913}},\"18\":{\"1\":{\"star_index\":2859751,\"get_star_ts\":1702877194},\"2\":{\"star_index\":2860321,\"get_star_ts\":1702877473}},\"17\":{\"2\":{\"get_star_ts\":1702791746,\"star_index\":2786366},\"1\":{\"get_star_ts\":1702790648,\"star_index\":2784845}}},\"id\":641575,\"stars\":36,\"name\":\"Marie Payne\",\"local_score\":1782},\"417433\":{\"id\":417433,\"completion_day_level\":{},\"global_score\":0,\"last_star_ts\":0,\"local_score\":0,\"name\":\"frigley1\",\"stars\":0},\"635231\":{\"stars\":0,\"local_score\":0,\"name\":\"aydanjiwani\",\"global_score\":0,\"last_star_ts\":0,\"completion_day_level\":{},\"id\":635231},\"996154\":{\"global_score\":0,\"last_star_ts\":0,\"completion_day_level\":{},\"id\":996154,\"stars\":0,\"name\":\"dracoyunho\",\"local_score\":0},\"1573427\":{\"id\":1573427,\"completion_day_level\":{},\"global_score\":0,\"last_star_ts\":0,\"name\":\"Jonathan Melcher\",\"local_score\":0,\"stars\":0},\"117917\":{\"id\":117917,\"completion_day_level\":{},\"global_score\":0,\"last_star_ts\":0,\"local_score\":0,\"name\":\"adambysice\",\"stars\":0},\"196695\":{\"global_score\":0,\"last_star_ts\":0,\"id\":196695,\"completion_day_level\":{},\"stars\":0,\"name\":\"Chuck Walker\",\"local_score\":0},\"1650861\":{\"name\":\"Mike Burns\",\"local_score\":0,\"stars\":0,\"id\":1650861,\"completion_day_level\":{},\"last_star_ts\":0,\"global_score\":0},\"123142\":{\"last_star_ts\":0,\"global_score\":0,\"id\":123142,\"completion_day_level\":{},\"stars\":0,\"name\":\"pingpongkingkong\",\"local_score\":0},\"250349\":{\"last_star_ts\":1702910238,\"global_score\":0,\"completion_day_level\":{\"13\":{\"2\":{\"get_star_ts\":1702495481,\"star_index\":2458457},\"1\":{\"get_star_ts\":1702490214,\"star_index\":2452163}},\"6\":{\"1\":{\"star_index\":1377163,\"get_star_ts\":1701874312},\"2\":{\"star_index\":1384795,\"get_star_ts\":1701877102}},\"16\":{\"1\":{\"star_index\":2741903,\"get_star_ts\":1702740574},\"2\":{\"star_index\":2743420,\"get_star_ts\":1702741753}},\"14\":{\"1\":{\"star_index\":2541036,\"get_star_ts\":1702565081},\"2\":{\"get_star_ts\":1702583200,\"star_index\":2562210}},\"12\":{\"2\":{\"get_star_ts\":1702696029,\"star_index\":2689724},\"1\":{\"get_star_ts\":1702393430,\"star_index\":2331242}},\"1\":{\"2\":{\"star_index\":387570,\"get_star_ts\":1701522787},\"1\":{\"star_index\":252054,\"get_star_ts\":1701480316}},\"4\":{\"2\":{\"star_index\":926957,\"get_star_ts\":1701703375},\"1\":{\"star_index\":908512,\"get_star_ts\":1701698474}},\"15\":{\"1\":{\"star_index\":2592777,\"get_star_ts\":1702617420},\"2\":{\"star_index\":2647337,\"get_star_ts\":1702650636}},\"18\":{\"1\":{\"get_star_ts\":1702910238,\"star_index\":2895833}},\"8\":{\"1\":{\"get_star_ts\":1702044795,\"star_index\":1744345},\"2\":{\"get_star_ts\":1702086600,\"star_index\":1820480}},\"2\":{\"2\":{\"get_star_ts\":1701573466,\"star_index\":527844},\"1\":{\"star_index\":527245,\"get_star_ts\":1701572913}},\"9\":{\"1\":{\"get_star_ts\":1702130674,\"star_index\":1914309},\"2\":{\"star_index\":1914961,\"get_star_ts\":1702130954}},\"3\":{\"2\":{\"star_index\":760401,\"get_star_ts\":1701656910},\"1\":{\"get_star_ts\":1701655810,\"star_index\":759087}},\"11\":{\"1\":{\"star_index\":2216109,\"get_star_ts\":1702308784},\"2\":{\"get_star_ts\":1702313060,\"star_index\":2224293}},\"5\":{\"1\":{\"get_star_ts\":1701791339,\"star_index\":1155763},\"2\":{\"get_star_ts\":1701916597,\"star_index\":1465291}},\"7\":{\"1\":{\"get_star_ts\":1701968138,\"star_index\":1583485},\"2\":{\"get_star_ts\":1701985838,\"star_index\":1623626}},\"10\":{\"1\":{\"star_index\":2062763,\"get_star_ts\":1702221098},\"2\":{\"star_index\":2079937,\"get_star_ts\":1702229599}}},\"id\":250349,\"stars\":33,\"local_score\":1501,\"name\":\"David McFadzean\"},\"118609\":{\"stars\":36,\"name\":\"Michael Tardibuono\",\"local_score\":1762,\"last_star_ts\":1702911232,\"global_score\":47,\"completion_day_level\":{\"2\":{\"1\":{\"star_index\":269129,\"get_star_ts\":1701493769},\"2\":{\"star_index\":270366,\"get_star_ts\":1701493911}},\"9\":{\"1\":{\"star_index\":1844567,\"get_star_ts\":1702101009},\"2\":{\"star_index\":1846073,\"get_star_ts\":1702101468}},\"3\":{\"1\":{\"star_index\":535171,\"get_star_ts\":1701580341},\"2\":{\"star_index\":536337,\"get_star_ts\":1701580670}},\"11\":{\"1\":{\"get_star_ts\":1702271971,\"star_index\":2138337},\"2\":{\"get_star_ts\":1702273080,\"star_index\":2143947}},\"15\":{\"1\":{\"get_star_ts\":1702617028,\"star_index\":2590592},\"2\":{\"get_star_ts\":1702618946,\"star_index\":2598263}},\"4\":{\"1\":{\"star_index\":773825,\"get_star_ts\":1701666549},\"2\":{\"get_star_ts\":1701666944,\"star_index\":778441}},\"8\":{\"2\":{\"star_index\":1669731,\"get_star_ts\":1702014842},\"1\":{\"get_star_ts\":1702012287,\"star_index\":1656276}},\"17\":{\"2\":{\"star_index\":2788915,\"get_star_ts\":1702794360},\"1\":{\"star_index\":2785756,\"get_star_ts\":1702791232}},\"18\":{\"2\":{\"get_star_ts\":1702911232,\"star_index\":2896887},\"1\":{\"star_index\":2859431,\"get_star_ts\":1702877048}},\"10\":{\"1\":{\"star_index\":2047281,\"get_star_ts\":1702212995},\"2\":{\"get_star_ts\":1702216023,\"star_index\":2053056}},\"5\":{\"2\":{\"star_index\":1471115,\"get_star_ts\":1701923529},\"1\":{\"get_star_ts\":1701754884,\"star_index\":1059396}},\"7\":{\"1\":{\"star_index\":1486200,\"get_star_ts\":1701929059},\"2\":{\"get_star_ts\":1701930895,\"star_index\":1492487}},\"6\":{\"2\":{\"star_index\":1254216,\"get_star_ts\":1701839199},\"1\":{\"get_star_ts\":1701839123,\"star_index\":1253507}},\"13\":{\"2\":{\"star_index\":2396337,\"get_star_ts\":1702448399},\"1\":{\"star_index\":2394717,\"get_star_ts\":1702447596}},\"16\":{\"2\":{\"star_index\":2695444,\"get_star_ts\":1702704709},\"1\":{\"get_star_ts\":1702704175,\"star_index\":2693872}},\"1\":{\"2\":{\"star_index\":1730,\"get_star_ts\":1701407156},\"1\":{\"star_index\":99,\"get_star_ts\":1701407024}},\"14\":{\"2\":{\"star_index\":2496669,\"get_star_ts\":1702532828},\"1\":{\"star_index\":2488934,\"get_star_ts\":1702530523}},\"12\":{\"2\":{\"star_index\":2338152,\"get_star_ts\":1702398325},\"1\":{\"star_index\":2284199,\"get_star_ts\":1702358648}}},\"id\":118609},\"174641\":{\"name\":\"dubyte\",\"local_score\":0,\"stars\":0,\"completion_day_level\":{},\"id\":174641,\"global_score\":0,\"last_star_ts\":0},\"294290\":{\"stars\":0,\"name\":\"ellen-h\",\"local_score\":0,\"global_score\":0,\"last_star_ts\":0,\"completion_day_level\":{},\"id\":294290},\"1965603\":{\"global_score\":0,\"last_star_ts\":0,\"id\":1965603,\"completion_day_level\":{},\"stars\":0,\"name\":\"Tony Secondo\",\"local_score\":0},\"200293\":{\"global_score\":0,\"last_star_ts\":0,\"id\":200293,\"completion_day_level\":{},\"stars\":0,\"name\":\"Stefan Tihanyi\",\"local_score\":0},\"543413\":{\"name\":\"kelly ma\",\"local_score\":0,\"stars\":0,\"completion_day_level\":{},\"id\":543413,\"last_star_ts\":0,\"global_score\":0},\"170332\":{\"id\":170332,\"completion_day_level\":{},\"global_score\":0,\"last_star_ts\":0,\"local_score\":0,\"name\":null,\"stars\":0},\"118311\":{\"completion_day_level\":{},\"id\":118311,\"global_score\":0,\"last_star_ts\":0,\"name\":\"Noah Kipin\",\"local_score\":0,\"stars\":0},\"186091\":{\"last_star_ts\":0,\"global_score\":0,\"id\":186091,\"completion_day_level\":{},\"stars\":0,\"local_score\":0,\"name\":\"Adam Harvie\"},\"388499\":{\"last_star_ts\":1702654124,\"global_score\":0,\"completion_day_level\":{\"1\":{\"1\":{\"star_index\":2648536,\"get_star_ts\":1702651489},\"2\":{\"star_index\":2652384,\"get_star_ts\":1702654124}}},\"id\":388499,\"stars\":2,\"name\":\"Marc-François Cochaux-Laberge\",\"local_score\":77},\"387136\":{\"last_star_ts\":0,\"global_score\":0,\"id\":387136,\"completion_day_level\":{},\"stars\":0,\"local_score\":0,\"name\":\"Thomas Fix\"},\"1680356\":{\"global_score\":0,\"last_star_ts\":0,\"completion_day_level\":{},\"id\":1680356,\"stars\":0,\"name\":\"cameronouellette\",\"local_score\":0},\"986209\":{\"stars\":0,\"local_score\":0,\"name\":null,\"last_star_ts\":0,\"global_score\":0,\"id\":986209,\"completion_day_level\":{}},\"1661893\":{\"global_score\":0,\"last_star_ts\":0,\"id\":1661893,\"completion_day_level\":{},\"stars\":0,\"local_score\":0,\"name\":\"li-ran\"},\"195615\":{\"completion_day_level\":{\"7\":{\"2\":{\"get_star_ts\":1701962003,\"star_index\":1568620},\"1\":{\"star_index\":1560050,\"get_star_ts\":1701958431}},\"5\":{\"1\":{\"get_star_ts\":1701787654,\"star_index\":1145746},\"2\":{\"star_index\":1193565,\"get_star_ts\":1701805538}},\"10\":{\"2\":{\"star_index\":2079186,\"get_star_ts\":1702229220},\"1\":{\"star_index\":2056068,\"get_star_ts\":1702217603}},\"8\":{\"1\":{\"get_star_ts\":1702044808,\"star_index\":1744382},\"2\":{\"star_index\":1750288,\"get_star_ts\":1702047171}},\"4\":{\"2\":{\"get_star_ts\":1701717710,\"star_index\":978707},\"1\":{\"star_index\":971657,\"get_star_ts\":1701715681}},\"15\":{\"1\":{\"get_star_ts\":1702650503,\"star_index\":2647125},\"2\":{\"get_star_ts\":1702652816,\"star_index\":2650605}},\"3\":{\"2\":{\"get_star_ts\":1701982640,\"star_index\":1616146},\"1\":{\"star_index\":1606830,\"get_star_ts\":1701978724}},\"11\":{\"2\":{\"get_star_ts\":1702308214,\"star_index\":2214991},\"1\":{\"star_index\":2212983,\"get_star_ts\":1702307133}},\"2\":{\"2\":{\"star_index\":388408,\"get_star_ts\":1701523019},\"1\":{\"get_star_ts\":1701522725,\"star_index\":387334}},\"9\":{\"2\":{\"star_index\":1970008,\"get_star_ts\":1702157430},\"1\":{\"get_star_ts\":1702157023,\"star_index\":1969266}},\"12\":{\"2\":{\"get_star_ts\":1702412814,\"star_index\":2357944},\"1\":{\"get_star_ts\":1702401382,\"star_index\":2342398}},\"14\":{\"2\":{\"get_star_ts\":1702649426,\"star_index\":2645657},\"1\":{\"star_index\":2551298,\"get_star_ts\":1702573293}},\"1\":{\"1\":{\"get_star_ts\":1701436467,\"star_index\":109111},\"2\":{\"get_star_ts\":1701440421,\"star_index\":123833}},\"16\":{\"1\":{\"get_star_ts\":1702736978,\"star_index\":2737516},\"2\":{\"star_index\":2738391,\"get_star_ts\":1702737680}},\"13\":{\"2\":{\"star_index\":2547083,\"get_star_ts\":1702569778},\"1\":{\"star_index\":2456265,\"get_star_ts\":1702493625}},\"6\":{\"1\":{\"get_star_ts\":1701886826,\"star_index\":1409732},\"2\":{\"get_star_ts\":1701887088,\"star_index\":1410371}}},\"id\":195615,\"global_score\":0,\"last_star_ts\":1702737680,\"name\":\"Daniel Shanker\",\"local_score\":1454,\"stars\":32},\"137785\":{\"stars\":0,\"name\":\"vincentr21\",\"local_score\":0,\"global_score\":0,\"last_star_ts\":0,\"completion_day_level\":{},\"id\":137785},\"186094\":{\"global_score\":0,\"last_star_ts\":1702139664,\"completion_day_level\":{\"3\":{\"1\":{\"star_index\":748661,\"get_star_ts\":1701648163},\"2\":{\"star_index\":751180,\"get_star_ts\":1701649729}},\"2\":{\"2\":{\"star_index\":739226,\"get_star_ts\":1701643740},\"1\":{\"star_index\":733825,\"get_star_ts\":1701641796}},\"9\":{\"2\":{\"star_index\":1935095,\"get_star_ts\":1702139664},\"1\":{\"get_star_ts\":1702139402,\"star_index\":1934531}},\"8\":{\"1\":{\"star_index\":1671961,\"get_star_ts\":1702015514},\"2\":{\"get_star_ts\":1702048667,\"star_index\":1753851}},\"6\":{\"1\":{\"star_index\":1463744,\"get_star_ts\":1701914868},\"2\":{\"star_index\":1463926,\"get_star_ts\":1701915059}},\"4\":{\"1\":{\"star_index\":794845,\"get_star_ts\":1701669177},\"2\":{\"star_index\":799527,\"get_star_ts\":1701670228}},\"1\":{\"2\":{\"get_star_ts\":1701581263,\"star_index\":539163},\"1\":{\"get_star_ts\":1701566958,\"star_index\":520227}},\"7\":{\"1\":{\"star_index\":1491421,\"get_star_ts\":1701930558},\"2\":{\"star_index\":1493558,\"get_star_ts\":1701931251}},\"5\":{\"2\":{\"star_index\":1282496,\"get_star_ts\":1701843360},\"1\":{\"get_star_ts\":1701757629,\"star_index\":1069155}}},\"id\":186094,\"stars\":18,\"local_score\":807,\"name\":\"Ricky Kwan\"},\"195610\":{\"stars\":0,\"local_score\":0,\"name\":\"eolalde\",\"global_score\":0,\"last_star_ts\":0,\"id\":195610,\"completion_day_level\":{}},\"990214\":{\"global_score\":0,\"last_star_ts\":0,\"completion_day_level\":{},\"id\":990214,\"stars\":0,\"local_score\":0,\"name\":\"emmawilsonix\"}}}";
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
