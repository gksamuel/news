--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.6
-- Dumped by pg_dump version 9.5.6

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: articles; Type: TABLE; Schema: public; Owner: news
--

CREATE TABLE articles (
    articleid integer NOT NULL,
    authorid integer NOT NULL,
    categoryid integer NOT NULL,
    title character varying(100) NOT NULL,
    summary character varying(400) NOT NULL,
    article text,
    image1 character varying(200),
    image2 character varying(200),
    sticky integer,
    statusid integer NOT NULL,
    pagerank integer DEFAULT 0 NOT NULL,
    datecreated timestamp with time zone NOT NULL,
    datemodified timestamp with time zone DEFAULT now() NOT NULL
);


ALTER TABLE articles OWNER TO news;

--
-- Name: articles_articleid_seq; Type: SEQUENCE; Schema: public; Owner: news
--

CREATE SEQUENCE articles_articleid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE articles_articleid_seq OWNER TO news;

--
-- Name: articles_articleid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: news
--

ALTER SEQUENCE articles_articleid_seq OWNED BY articles.articleid;


--
-- Name: authors; Type: TABLE; Schema: public; Owner: news
--

CREATE TABLE authors (
    authorid integer NOT NULL,
    username character varying(10) NOT NULL,
    password character varying(60) NOT NULL,
    firstname character varying(20) NOT NULL,
    lastname character varying(20) NOT NULL,
    mobilenumber bigint NOT NULL,
    statusid integer NOT NULL,
    datecreated timestamp with time zone NOT NULL,
    datemodified timestamp with time zone DEFAULT now() NOT NULL
);


ALTER TABLE authors OWNER TO news;

--
-- Name: authors_authorid_seq; Type: SEQUENCE; Schema: public; Owner: news
--

CREATE SEQUENCE authors_authorid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE authors_authorid_seq OWNER TO news;

--
-- Name: authors_authorid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: news
--

ALTER SEQUENCE authors_authorid_seq OWNED BY authors.authorid;


--
-- Name: categories; Type: TABLE; Schema: public; Owner: news
--

CREATE TABLE categories (
    categoryid integer NOT NULL,
    category character varying(20) NOT NULL,
    description character varying(100) NOT NULL,
    datecreated timestamp with time zone NOT NULL,
    datemodified timestamp with time zone DEFAULT now() NOT NULL
);


ALTER TABLE categories OWNER TO news;

--
-- Name: categories_categoryid_seq; Type: SEQUENCE; Schema: public; Owner: news
--

CREATE SEQUENCE categories_categoryid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE categories_categoryid_seq OWNER TO news;

--
-- Name: categories_categoryid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: news
--

ALTER SEQUENCE categories_categoryid_seq OWNED BY categories.categoryid;


--
-- Name: configs; Type: TABLE; Schema: public; Owner: news
--

CREATE TABLE configs (
    configid integer NOT NULL,
    name character varying(10) NOT NULL,
    value character varying(100) NOT NULL,
    description character varying(100),
    datecreated timestamp with time zone NOT NULL,
    datemodified timestamp with time zone DEFAULT now() NOT NULL
);


ALTER TABLE configs OWNER TO news;

--
-- Name: configs_configid_seq; Type: SEQUENCE; Schema: public; Owner: news
--

CREATE SEQUENCE configs_configid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE configs_configid_seq OWNER TO news;

--
-- Name: configs_configid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: news
--

ALTER SEQUENCE configs_configid_seq OWNED BY configs.configid;


--
-- Name: pageviews; Type: TABLE; Schema: public; Owner: news
--

CREATE TABLE pageviews (
    pageviewid integer NOT NULL,
    articleid integer NOT NULL,
    ipaddress character varying(40),
    datecreated timestamp with time zone DEFAULT now() NOT NULL
);


ALTER TABLE pageviews OWNER TO news;

--
-- Name: pageviews_pageviewid_seq; Type: SEQUENCE; Schema: public; Owner: news
--

CREATE SEQUENCE pageviews_pageviewid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE pageviews_pageviewid_seq OWNER TO news;

--
-- Name: pageviews_pageviewid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: news
--

ALTER SEQUENCE pageviews_pageviewid_seq OWNED BY pageviews.pageviewid;


--
-- Name: status; Type: TABLE; Schema: public; Owner: news
--

CREATE TABLE status (
    statusid integer NOT NULL,
    description character varying(100) NOT NULL,
    datecreated timestamp with time zone NOT NULL,
    datemodified timestamp with time zone DEFAULT now() NOT NULL
);


ALTER TABLE status OWNER TO news;

--
-- Name: status_statusid_seq; Type: SEQUENCE; Schema: public; Owner: news
--

CREATE SEQUENCE status_statusid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE status_statusid_seq OWNER TO news;

--
-- Name: status_statusid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: news
--

ALTER SEQUENCE status_statusid_seq OWNED BY status.statusid;


--
-- Name: articleid; Type: DEFAULT; Schema: public; Owner: news
--

ALTER TABLE ONLY articles ALTER COLUMN articleid SET DEFAULT nextval('articles_articleid_seq'::regclass);


--
-- Name: authorid; Type: DEFAULT; Schema: public; Owner: news
--

ALTER TABLE ONLY authors ALTER COLUMN authorid SET DEFAULT nextval('authors_authorid_seq'::regclass);


--
-- Name: categoryid; Type: DEFAULT; Schema: public; Owner: news
--

ALTER TABLE ONLY categories ALTER COLUMN categoryid SET DEFAULT nextval('categories_categoryid_seq'::regclass);


--
-- Name: configid; Type: DEFAULT; Schema: public; Owner: news
--

ALTER TABLE ONLY configs ALTER COLUMN configid SET DEFAULT nextval('configs_configid_seq'::regclass);


--
-- Name: pageviewid; Type: DEFAULT; Schema: public; Owner: news
--

ALTER TABLE ONLY pageviews ALTER COLUMN pageviewid SET DEFAULT nextval('pageviews_pageviewid_seq'::regclass);


--
-- Name: statusid; Type: DEFAULT; Schema: public; Owner: news
--

ALTER TABLE ONLY status ALTER COLUMN statusid SET DEFAULT nextval('status_statusid_seq'::regclass);


--
-- Data for Name: articles; Type: TABLE DATA; Schema: public; Owner: news
--

COPY articles (articleid, authorid, categoryid, title, summary, article, image1, image2, sticky, statusid, pagerank, datecreated, datemodified) FROM stdin;
33	1	4	Labour's Corbyn puts politics center stage at Glastonbury Festival	GLASTONBURY, England (Reuters) - British opposition leader Jeremy Corbyn got a rock star reception at Glastonbury Festival on Saturday, telling a headliner-sized crowd that millions of young people who voted for him would not be silenced or sidelined.	\N	\N	\N	\N	1	1000	2017-06-25 12:23:47+03	2017-06-26 14:24:03.526+03
51	1	6	No definite arrangements in place for Putin-Trump meeting: Kremlin	MOSCOW (Reuters) - The Kremlin said on Monday it was still premature to say anything certain about a possible meeting between Russian President Vladimir Putin and his U.S. counterpart Donald Trump next month because no definite arrangements had been made for it.	\N	\N	\N	\N	1	1000	2017-06-26 13:43:58+03	2017-06-26 14:25:02.744+03
35	1	4	Box Office: 'Transformers: The Last Knight' Opens to Franchise Low $69.1 Million	LOS ANGELES (Variety.com) - It seems the "Transformers" franchise is rusty.	\N	\N	\N	\N	1	1000	2017-06-25 19:01:37+03	2017-06-26 14:24:03.525+03
69	1	6	New York Pride marchers target Trump as San Francisco parties	NEW YORK/SAN FRANCISCO (Reuters) - Large crowds turned out for Pride marches on Sunday in New York City and San Francisco, the two U.S. places most associated with the lesbian, gay, bisexual and transgender rights movement, with the East Coast city bringing a more political flavor to the event sparked by events there almost 50 years ago.	\N	\N	\N	\N	1	1000	2017-06-26 10:31:53+03	2017-06-26 14:27:30.716+03
44	1	5	China jails Crown Resorts staff over gambling crimes	SHANGHAI (Reuters) - A Chinese court jailed 16 employees of Crown Resorts Ltd, including three Australians, in a quick-fire trial on Monday that caps a lengthy probe into how the firm lured Chinese high-rollers to its casinos.	\N	\N	\N	\N	1	1000	2017-06-26 13:28:30+03	2017-06-26 14:24:40.855+03
15	1	2	Facebook in talks to produce original TV-quality shows: WSJ	(Reuters) - Facebook Inc  is in talks with Hollywood studios about producing scripted, TV-quality shows, with an aim of launching original programming by late summer, the Wall Street Journal reported on Sunday.	\N	\N	\N	\N	1	1000	2017-06-26 10:27:39+03	2017-06-26 14:22:51.772+03
16	1	2	China, Canada vow not to conduct cyber attacks on private sector	(Reuters) - China and Canada have signed an agreement vowing not to conduct state-sponsored cyber attacks against each other aimed at stealing trade secrets or other confidential business information.	\N	\N	\N	\N	1	1000	2017-06-26 13:22:26+03	2017-06-26 14:22:51.773+03
17	1	2	Western Digital won't consent to SK Hynix participation in Toshiba chip unit sale	TOKYO (Reuters) - Western Digital Corp  has told Toshiba Corp  that it will not agree to a sale of the Japanese conglomerate's prized memory chip unit to a preferred bidding consortium that includes rival chipmaker SK Hynix Inc .	\N	\N	\N	\N	1	1000	2017-06-26 12:36:54+03	2017-06-26 14:22:51.772+03
58	1	6	Key Republican Collins has 'serious concerns' on healthcare bill	WASHINGTON (Reuters) - Republican Senator Susan Collins of Maine said on Sunday she has extreme reservations about the U.S. Senate's healthcare overhaul and does not think it will be able to pass this week.	\N	\N	\N	\N	1	1000	2017-06-26 06:23:02+03	2017-06-26 14:25:02.745+03
1	1	1	UK PM May strikes deal to get Northern Irish DUP support for minority government	LONDON (Reuters) - British Prime Minister Theresa May struck a deal on Monday to prop up her minority government with the support of a small Northern Irish Protestant party.	\N	\N	\N	\N	1	1	2017-06-26 14:12:09+03	2017-06-26 14:22:23.672+03
2	1	1	Italy's center-right wins in local elections, in blow to Renzi	ROME (Reuters) - Italy's center-right parties hammered their center-left rivals in mayoral elections, official results showed on Monday, putting pressure on the ruling Democratic Party (PD) ahead of a national vote due in less than a year.	\N	\N	\N	\N	1	2	2017-06-26 13:54:40+03	2017-06-26 14:22:23.675+03
3	1	1	New U.S. ambassador to China says North Korea a top priority	BEIJING (Reuters) - The new U.S. ambassador to China has said that stopping the threat posed by North Korea will be a top priority, along with resolving the U.S.-China trade imbalance, according to a video message to the Chinese people released on Monday.	\N	\N	\N	\N	1	4	2017-06-26 07:10:12+03	2017-06-26 14:22:23.673+03
53	1	6	Top Democrat slams Obama administration's response to Russian hacks	WASHINGTON (Reuters) - The top Democrat on the U.S. House Intelligence Committee on Sunday criticized the administration of former President Barack Obama for not taking earlier and tougher action against Russia for its alleged hacks aimed at swaying the Nov. 8 election for Donald Trump.	\N	\N	\N	\N	1	1000	2017-06-25 23:39:52+03	2017-06-26 14:25:02.746+03
14	1	2	CEO of Raytheon's Forcepoint eyes IPO: Boersen-Zeitung	FRANKFURT (Reuters) - U.S. missile maker Raytheon's  cybersecurity unit could thrive were it to be listed separately, the head of the unit, Forcepoint, told German business daily Boersenzeitung in an interview published on Saturday.	\N	\N	\N	\N	1	1000	2017-06-24 20:33:52+03	2017-06-26 14:22:51.774+03
18	1	2	Russia, upping pressure on Telegram app, says terrorists use it	MOSCOW (Reuters) - Russia's FSB security service said on Monday that the Telegram messaging app had been used by terrorists to plot atrocities on Russian soil, increasing pressure on the service days after the authorities accused it of violating Russian legislation.	\N	\N	\N	\N	1	1000	2017-06-26 14:11:11+03	2017-06-26 14:22:51.772+03
19	1	2	EU court seen ruling on Intel antitrust case next year: judge	PARIS (Reuters) - Europe's top court is likely to rule on Intel's  appeal against a record 1.06 billion euro ($1.19 billion) EU antitrust fine next year, an EU judge said on Monday, a case that may affect companies such as Google  and Qualcomm  in the EU's crosshairs.	\N	\N	\N	\N	1	1000	2017-06-26 12:08:48+03	2017-06-26 14:22:51.773+03
59	1	6	New York Pride marchers target Trump as San Francisco parties	NEW YORK/SAN FRANCISCO (Reuters) - Large crowds turned out for Pride marches on Sunday in New York City and San Francisco, the two U.S. places most associated with the lesbian, gay, bisexual and transgender rights movement, with the East Coast city bringing a more political flavor to the event sparked by events there almost 50 years ago.	\N	\N	\N	\N	1	1000	2017-06-26 10:31:53+03	2017-06-26 14:25:02.747+03
36	1	4	Cosby plans sex assault talks, accusers' lawyer cries foul	(Reuters) - Bill Cosby plans to conduct a series of free public seminars about sexual assault this summer, his spokesman said days after a Pennsylvania judge declared a mistrial in the entertainer's sexual assault trial.	\N	\N	\N	\N	1	1000	2017-06-22 23:46:40+03	2017-06-26 14:24:03.528+03
8	1	1	'Concerning' that buildings are failing safety tests: UK PM's spokesman	LONDON (Reuters) - It is "concerning" that all the tower blocks tested for combustible building materials after a devastating fire in west London have failed, a spokesman for British Prime Minister Theresa May said on Monday.	\N	\N	\N	\N	1	3	2017-06-26 14:01:39+03	2017-06-26 14:22:23.673+03
9	1	1	Trump, Modi seek rapport despite friction on trade, immigration	WASHINGTON (Reuters) - U.S. President Donald Trump and Indian Prime Minister Narendra Modi will hold their first face-to-face meeting in Washington on Monday, seeking to boost U.S.-Indian relations despite differences over trade, the Paris climate accord and immigration.	\N	\N	\N	\N	1	6	2017-06-26 08:26:26+03	2017-06-26 14:22:23.674+03
22	1	3	China seeks ping-pong diplomacy to defuse coaching row	BEIJING (Reuters) - The removal of China's top table tennis coach has stirred up rare tensions in the country's sporting world, prompting a backlash from leading players and fans, and drawing the gaze of the country's censors.	\N	\N	\N	\N	1	7	2017-06-26 13:29:20+03	2017-06-26 14:23:21.068+03
10	1	1	Asylum seekers in Canada who fled Trump now trapped in legal limbo	TORONTO/WINNIPEG, Manitoba (Reuters) - Thousands of people who fled to Canada to escape President Donald Trump’s crackdown on illegal migrants have become trapped in legal limbo because of an overburdened refugee system, struggling to find work, permanent housing or enroll their children in schools.	\N	\N	\N	\N	1	10	2017-06-26 14:11:37+03	2017-06-26 14:22:23.674+03
4	1	1	Burn victims overwhelm Pakistani hospitals after tanker fire kills 146	LAHORE, Pakistan (Reuters) - Pakistani hospitals on Monday struggled to treat scores of severely burned victims of a fuel tanker explosion that killed at least 146 people, as Prime Minister Nawaz Sharif flew back from an overseas trip to visit the injured.	\N	\N	\N	\N	1	14	2017-06-26 12:25:22+03	2017-06-26 14:22:23.675+03
45	1	5	Oil edges above November lows but under threat from U.S. supply surge	LONDON (Reuters) - Oil rose for a third straight session on Monday, as speculators took advantage of last week's drop to seven-month lows, although a relentless increase in U.S. supply and little evidence of a widespread drop in global inventories capped gains.	\N	\N	\N	\N	1	1000	2017-06-26 12:00:59+03	2017-06-26 14:24:40.852+03
7	1	1	Mosul battle to end in days as troops advance in Old City: Iraqi general	MOSUL, Iraq (Reuters) - The battle to take full control of Mosul from Islamic State will be over in a few days and an attempted fight-back by the militants failed, an Iraqi general told Reuters on Monday.	\N	\N	\N	\N	1	1000	2017-06-26 13:20:23+03	2017-06-26 14:22:23.673+03
11	1	2	Short of IT workers at home, Israeli startups recruit elsewhere	TEL AVIV (Reuters) - When Alexey Chalimov founded software design firm Eastern Peak in Israel four years ago he knew he would not find the developers he needed at home.	\N	\N	\N	\N	1	1000	2017-06-26 08:35:49+03	2017-06-26 14:22:51.771+03
39	1	4	Actor Johnny Depp apologizes for 'poor taste' Trump assassination joke	GLASTONBURY, England (Reuters) - Johnny Depp on Friday apologized for joking about assassinating U.S. President Donald Trump, saying his remarks were in "poor taste."	\N	\N	\N	\N	1	1000	2017-06-23 22:14:19+03	2017-06-26 14:24:03.527+03
46	1	5	Facebook in talks to produce original TV-quality shows: WSJ	(Reuters) - Facebook Inc  is in talks with Hollywood studios about producing scripted, TV-quality shows, with an aim of launching original programming by late summer, the Wall Street Journal reported on Sunday.	\N	\N	\N	\N	1	5	2017-06-26 10:27:39+03	2017-06-26 14:24:40.854+03
12	1	2	Even with Whole Foods, Amazon would need many more warehouses to reshape grocery delivery	(Reuters) - If Amazon.com Inc  hopes to revolutionize grocery delivery, then its bid to buy Whole Foods Market Inc  for $13.7 billion will be just the start of a long and costly process.	\N	\N	\N	\N	1	8	2017-06-24 23:25:10+03	2017-06-26 14:22:51.774+03
34	1	4	Harry Potter still casts spell for fans 20 years on	LONDON (Reuters) - Dressed in a long black gown and holding a wand, George Massingham is keen for everyone to know he is a Harry Potter super fan.	\N	\N	\N	\N	1	15	2017-06-22 19:37:20+03	2017-06-26 14:24:03.528+03
5	1	1	Exclusive: U.S. warship stayed on deadly collision course despite warning - container ship captain	TOKYO (Reuters) - A U.S. warship struck by a container vessel in Japanese waters failed to respond to warning signals or take evasive action before a collision that killed seven of its crew, according to a report of the incident by the Philippine cargo ship's captain.	\N	\N	\N	\N	1	16	2017-06-26 14:05:41+03	2017-06-26 14:22:23.672+03
20	1	2	British lawmakers hit by 'sustained' cyber attack	LONDON (Reuters) - Britain's parliament was hit by a "sustained and determined" cyber attack on Saturday designed to identify weak email passwords, just over a month after a ransomware worm crippled parts of the country's health service.	\N	\N	\N	\N	1	1000	2017-06-24 22:02:38+03	2017-06-26 14:22:51.774+03
21	1	3	Spieth holes bunker shot to win Travelers in playoff	(Reuters) - Jordan Spieth holed a bunker shot to win the Travelers Championship in a playoff on Sunday and become the second youngest player in the modern era behind Tiger Woods to post 10 PGA Tour victories.	\N	\N	\N	\N	1	1000	2017-06-26 03:20:25+03	2017-06-26 14:23:21.065+03
43	1	5	'Pharma bro' Martin Shkreli heads into fraud trial	NEW YORK (Reuters) - Martin Shkreli, the pharmaceutical entrepreneur vilified as the "pharma bro" for raising the price of a life-saving drug by 5,000 percent, will go on trial on Monday for what U.S. prosecutors called a Ponzi-like scheme at his former hedge fund and a drug company he once ran.	\N	\N	\N	\N	1	1000	2017-06-26 13:08:19+03	2017-06-26 14:24:40.853+03
55	1	6	Trump, Modi seek rapport despite friction on trade, immigration	WASHINGTON (Reuters) - U.S. President Donald Trump and Indian Prime Minister Narendra Modi will hold their first face-to-face meeting in Washington on Monday, seeking to boost U.S.-Indian relations despite differences over trade, the Paris climate accord and immigration.	\N	\N	\N	\N	1	1000	2017-06-26 08:26:26+03	2017-06-26 14:25:02.744+03
56	1	6	Conservative activists hold muted rallies in Washington	WASHINGTON (Reuters) - Conservative activists held a pair of rallies in Washington on Sunday to decry the handful of celebrities who have joked about violence against President Donald Trump and to protest efforts to stop contentious speakers at colleges.	\N	\N	\N	\N	1	1000	2017-06-25 23:11:41+03	2017-06-26 14:25:02.748+03
57	1	6	Conservative Koch network criticizes U.S. Senate healthcare bill	COLORADO SPRINGS, Colo. (Reuters) - Officials with the conservative U.S. political network overseen by the Koch brothers say they are unhappy with the healthcare bill that may be voted on by the Senate this week and will lobby for changes to it.	\N	\N	\N	\N	1	1000	2017-06-25 21:53:35+03	2017-06-26 14:25:02.745+03
52	1	6	Trump reaches out to lawmakers on healthcare as another says 'no'	WASHINGTON (Reuters) - President Donald Trump made calls to fellow Republicans in the U.S. Senate on Friday to mobilize support for their party's healthcare overhaul while acknowledging the legislation is on a "very, very narrow path" to passage.	\N	\N	\N	\N	1	1000	2017-06-24 18:53:55+03	2017-06-26 14:25:02.749+03
54	1	6	Top Senate Democrat Schumer: 50-50 chance Republicans pass healthcare bill	WASHINGTON (Reuters) - U.S. Senate Democratic Leader Chuck Schumer said on Sunday that he sees a 50 percent probability that Republicans will be able to pass their healthcare bill.	\N	\N	\N	\N	1	1000	2017-06-25 16:41:31+03	2017-06-26 14:25:02.749+03
70	1	6	Senator Rand Paul says would consider partial repeal of Obamacare	WASHINGTON (Reuters) - U.S. Republican Senator Rand Paul said on Sunday that he remains open to supporting the Senate healthcare bill but only under certain circumstances.	\N	\N	\N	\N	1	1000	2017-06-25 17:09:07+03	2017-06-26 14:27:30.717+03
13	1	2	Australia to seek greater powers on encrypted messaging at 'Five eyes' meeting	SYDNEY (Reuters) - Australia said on Sunday it will push for greater powers to tackle the use of encrypted messaging services used by terrorists and criminals at an upcoming meeting of ministers from the "Five Eyes" intelligence network.	\N	\N	\N	\N	1	1000	2017-06-25 17:31:43+03	2017-06-26 14:22:51.772+03
47	1	5	Japanese airbag maker Takata files for bankruptcy, gets U.S. sponsor	TOKYO (Reuters) - Japan's Takata Corp , the firm at the center of the auto industry's biggest ever product recall, filed for bankruptcy protection in the United States and Japan, and said it would be bought for $1.6 billion by U.S.-based rival Key Safety Systems.	\N	\N	\N	\N	1	1000	2017-06-26 13:49:17+03	2017-06-26 14:24:40.852+03
48	1	5	U.S. activist presses for 'bold action' at Nestle	(Reuters) - Nestle  is under pressure from U.S. activist shareholder Third Point, which has taken a $3.5 billion stake in the food maker and is pushing Europe's largest company to improve margins, buy back shares and get rid of non-core businesses.	\N	\N	\N	\N	1	1000	2017-06-26 13:46:54+03	2017-06-26 14:24:40.852+03
49	1	5	Clogged oil arteries slow U.S. shale rush to record output	GUERNSEY, WYOMING (Reuters) - A gallon of gasoline that allows a driver on the U.S. East Coast to travel about 25 miles has already navigated thousands of miles from an oil field to one of the world's largest fuel markets.	\N	\N	\N	\N	1	1000	2017-06-26 11:15:12+03	2017-06-26 14:24:40.854+03
50	1	5	EU court seen ruling on Intel antitrust case next year: judge	PARIS (Reuters) - Europe's top court is likely to rule on Intel's  appeal against a record 1.06 billion euro ($1.19 billion) EU antitrust fine next year, an EU judge said on Monday, a case that may affect companies such as Google  and Qualcomm  in the EU's crosshairs.	\N	\N	\N	\N	1	1000	2017-06-26 12:08:48+03	2017-06-26 14:24:40.855+03
37	1	4	Barry Gibb brings Bee Gee's disco fever to Glasto's legends slot	GLASTONBURY, England (Reuters) - Barry Gibb brought some Sunday afternoon fever to the legends slot at Glastonbury Festival when he entertained a huge crowd with Bee Gees hits "Stayin' Alive", "How Deep is Your Love", "Night Fever" and "Tragedy".	\N	\N	\N	\N	1	1000	2017-06-25 22:26:18+03	2017-06-26 14:24:03.525+03
38	1	4	Radiohead take aim at 'strong and stable' May at Glastonbury	GLASTONBURY, England (Reuters) - Britain's Radiohead returned to Glastonbury's Pyramid Stage on Friday, 20 years after a legendary performance at the festival, with a set that mocked Prime Minister Theresa May's election campaign and pulled songs from nearly all of their albums.	\N	\N	\N	\N	1	1000	2017-06-24 03:38:06+03	2017-06-26 14:24:03.526+03
40	1	4	Ron Howard enters 'Star Wars' after Han Solo movie directors depart	LOS ANGELES (Reuters) - Oscar-winning filmmaker Ron Howard is stepping into a galaxy far, far away for the upcoming "Star Wars" Han Solo spin-off movie, Lucasfilm announced on Thursday, after the film's production was thrown into upheaval by the surprise departure of its directors.	\N	\N	\N	\N	1	1000	2017-06-23 03:19:26+03	2017-06-26 14:24:03.528+03
41	1	5	Italy bank deal lifts Europe shares, dollar on back foot	LONDON (Reuters) - Shares rose in Europe on Monday, with Italian banks gaining after a deal to wind up two failed regional lenders, while the dollar and U.S. bond yields held close to recent lows as subdued inflation raised questions over the outlook for monetary policy.	\N	\N	\N	\N	1	1000	2017-06-26 11:38:15+03	2017-06-26 14:24:40.851+03
42	1	5	Time Inc, Barclays to launch Fortune 500 stock indices	NEW YORK (Reuters) - Time Inc  said Monday it will license its Fortune brand for stock indexes based on the Fortune 500 in a new partnership with Barclays PLC  in an effort to diversify Time's revenue into the growing index-investing business.	\N	\N	\N	\N	1	1000	2017-06-26 14:04:45+03	2017-06-26 14:24:40.855+03
24	1	3	Athletics: Japan prodigy Sani Brown eyes big scalps in London	TOKYO (Reuters) - Japanese teenager Abdul Hakim Sani Brown is eyeing bigger scalps at the world championships in London after completing an impressive sprint double of national titles over the weekend.	\N	\N	\N	\N	1	1000	2017-06-26 07:25:12+03	2017-06-26 14:23:21.068+03
26	1	3	Federer wielding SABR with devastating effect	LONDON (Reuters) - What started as a bit of fun may just have led to a masterstroke.	\N	\N	\N	\N	1	1000	2017-06-26 04:07:48+03	2017-06-26 14:23:21.068+03
27	1	3	North Korea dismiss Moon call for united Pyeongchang team	SEOUL (Reuters) - A leading North Korean sports official believes it is too late to consider South Korean President Moon Jae-in's proposal to form a unified team for the 2018 Winter Olympics in Pyeongchang, saying that political tension must be resolved first.	\N	\N	\N	\N	1	1000	2017-06-26 12:44:51+03	2017-06-26 14:23:21.066+03
28	1	3	New Zealand pedal their way to brink of America's Cup glory	HAMILTON, Bermuda (Reuters) - Emirates Team New Zealand will take to Bermuda's Great Sound on Monday with a mission, to finally wipe out the hurt inflicted on the sports-mad country by Oracle Team USA in the America's Cup.	\N	\N	\N	\N	1	1000	2017-06-26 05:21:18+03	2017-06-26 14:23:21.066+03
30	1	3	The gloves are off between Hamilton and Vettel	LONDON (Reuters) - The gloves are off and the fight is on between Formula One title rivals Sebastian Vettel and Lewis Hamilton.	\N	\N	\N	\N	1	1000	2017-06-26 12:03:24+03	2017-06-26 14:23:21.068+03
31	1	4	Facebook in talks to produce original TV-quality shows: WSJ	(Reuters) - Facebook Inc  is in talks with Hollywood studios about producing scripted, TV-quality shows, with an aim of launching original programming by late summer, the Wall Street Journal reported on Sunday.	\N	\N	\N	\N	1	1000	2017-06-26 10:27:39+03	2017-06-26 14:24:03.524+03
32	1	4	Stars come out for European premiere of Edgar Wright's 'Baby Driver'	LONDON (Reuters) - Big Hollywood names hit the red carpet in London on Wednesday for the European premiere of British director Edgar Wright's new heist movie "Baby Driver".	\N	\N	\N	\N	1	1000	2017-06-22 19:28:17+03	2017-06-26 14:24:03.529+03
29	1	3	Athletics: Coleman stunned in 200 as Merritt, Bowie also lose	SACRAMENTO, California (Reuters) - Rising star Christian Coleman's weekend of disappointment continued on Sunday as he lost the 200 metres at the U.S. nationals and world championships trials to Ameer Webb.	\N	\N	\N	\N	1	11	2017-06-26 03:17:23+03	2017-06-26 14:23:21.067+03
23	1	3	Wimbledon most open for a decade, says contender Lopez	LONDON (Reuters) - This year's men's singles at Wimbledon promises to be the most open for a decade, according to Spanish veteran Feliciano Lopez, who is quietly fancying his own chances of a deep run.	\N	\N	\N	\N	1	12	2017-06-26 05:11:20+03	2017-06-26 14:23:21.066+03
25	1	3	Foot injury dents Muir hopes of doubling up in London	(Reuters) - British middle distance runner Laura Muir is expected to abandon her bid to double up at the world championships in London after a foot injury hampered her preparations for the August event.	\N	\N	\N	\N	1	13	2017-06-26 13:00:50+03	2017-06-26 14:23:21.065+03
6	1	1	Hong Kong's youth press campaign despite China's rejection of full democracy	HONG KONG/BEIJING (Reuters) - When the British handed over Hong Kong to China in 1997, Beijing promised to allow universal suffrage as an "ultimate aim", along with other freedoms, under a "one country, two systems" arrangement agreed with London.	\N	\N	\N	\N	1	9	2017-06-26 14:03:15+03	2017-06-26 14:22:23.674+03
60	1	6	Senator Rand Paul says would consider partial repeal of Obamacare	WASHINGTON (Reuters) - U.S. Republican Senator Rand Paul said on Sunday that he remains open to supporting the Senate healthcare bill but only under certain circumstances.	\N	\N	\N	\N	1	1000	2017-06-25 17:09:07+03	2017-06-26 14:25:02.748+03
61	1	6	No definite arrangements in place for Putin-Trump meeting: Kremlin	MOSCOW (Reuters) - The Kremlin said on Monday it was still premature to say anything certain about a possible meeting between Russian President Vladimir Putin and his U.S. counterpart Donald Trump next month because no definite arrangements had been made for it.	\N	\N	\N	\N	1	1000	2017-06-26 13:43:58+03	2017-06-26 14:27:30.714+03
62	1	6	Trump reaches out to lawmakers on healthcare as another says 'no'	WASHINGTON (Reuters) - President Donald Trump made calls to fellow Republicans in the U.S. Senate on Friday to mobilize support for their party's healthcare overhaul while acknowledging the legislation is on a "very, very narrow path" to passage.	\N	\N	\N	\N	1	1000	2017-06-24 18:53:55+03	2017-06-26 14:27:30.718+03
63	1	6	Top Democrat slams Obama administration's response to Russian hacks	WASHINGTON (Reuters) - The top Democrat on the U.S. House Intelligence Committee on Sunday criticized the administration of former President Barack Obama for not taking earlier and tougher action against Russia for its alleged hacks aimed at swaying the Nov. 8 election for Donald Trump.	\N	\N	\N	\N	1	1000	2017-06-25 23:39:52+03	2017-06-26 14:27:30.715+03
64	1	6	Top Senate Democrat Schumer: 50-50 chance Republicans pass healthcare bill	WASHINGTON (Reuters) - U.S. Senate Democratic Leader Chuck Schumer said on Sunday that he sees a 50 percent probability that Republicans will be able to pass their healthcare bill.	\N	\N	\N	\N	1	1000	2017-06-25 16:41:31+03	2017-06-26 14:27:30.717+03
65	1	6	Trump, Modi seek rapport despite friction on trade, immigration	WASHINGTON (Reuters) - U.S. President Donald Trump and Indian Prime Minister Narendra Modi will hold their first face-to-face meeting in Washington on Monday, seeking to boost U.S.-Indian relations despite differences over trade, the Paris climate accord and immigration.	\N	\N	\N	\N	1	1000	2017-06-26 08:26:26+03	2017-06-26 14:27:30.714+03
66	1	6	Conservative activists hold muted rallies in Washington	WASHINGTON (Reuters) - Conservative activists held a pair of rallies in Washington on Sunday to decry the handful of celebrities who have joked about violence against President Donald Trump and to protest efforts to stop contentious speakers at colleges.	\N	\N	\N	\N	1	1000	2017-06-25 23:11:41+03	2017-06-26 14:27:30.717+03
67	1	6	Conservative Koch network criticizes U.S. Senate healthcare bill	COLORADO SPRINGS, Colo. (Reuters) - Officials with the conservative U.S. political network overseen by the Koch brothers say they are unhappy with the healthcare bill that may be voted on by the Senate this week and will lobby for changes to it.	\N	\N	\N	\N	1	1000	2017-06-25 21:53:35+03	2017-06-26 14:27:30.714+03
68	1	6	Key Republican Collins has 'serious concerns' on healthcare bill	WASHINGTON (Reuters) - Republican Senator Susan Collins of Maine said on Sunday she has extreme reservations about the U.S. Senate's healthcare overhaul and does not think it will be able to pass this week.	\N	\N	\N	\N	1	1000	2017-06-26 06:23:02+03	2017-06-26 14:27:30.715+03
\.


--
-- Name: articles_articleid_seq; Type: SEQUENCE SET; Schema: public; Owner: news
--

SELECT pg_catalog.setval('articles_articleid_seq', 70, true);


--
-- Data for Name: authors; Type: TABLE DATA; Schema: public; Owner: news
--

COPY authors (authorid, username, password, firstname, lastname, mobilenumber, statusid, datecreated, datemodified) FROM stdin;
1	sgachanja	x	Samuel	Gachanja	25472092093	1	2017-06-25 17:41:19.993325+03	2017-06-25 17:41:19.993325+03
\.


--
-- Name: authors_authorid_seq; Type: SEQUENCE SET; Schema: public; Owner: news
--

SELECT pg_catalog.setval('authors_authorid_seq', 1, true);


--
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: news
--

COPY categories (categoryid, category, description, datecreated, datemodified) FROM stdin;
1	World News	News from around the world	2017-06-26 09:41:39.791403+03	2017-06-26 09:41:39.791403+03
2	Technology News	Computers and stuff	2017-06-26 09:42:20.751208+03	2017-06-26 09:42:20.751208+03
3	Sports News	Your daily soccor meal	2017-06-26 09:42:43.967068+03	2017-06-26 09:42:43.967068+03
4	Entertainment News	Your daily doze of gossip	2017-06-26 09:43:04.510921+03	2017-06-26 09:43:04.510921+03
5	Business News	Money Matters	2017-06-26 09:43:32.038611+03	2017-06-26 09:43:32.038611+03
6	Politics	Lies and more lies	2017-06-26 09:43:56.750561+03	2017-06-26 09:43:56.750561+03
\.


--
-- Name: categories_categoryid_seq; Type: SEQUENCE SET; Schema: public; Owner: news
--

SELECT pg_catalog.setval('categories_categoryid_seq', 6, true);


--
-- Data for Name: configs; Type: TABLE DATA; Schema: public; Owner: news
--

COPY configs (configid, name, value, description, datecreated, datemodified) FROM stdin;
\.


--
-- Name: configs_configid_seq; Type: SEQUENCE SET; Schema: public; Owner: news
--

SELECT pg_catalog.setval('configs_configid_seq', 1, false);


--
-- Data for Name: pageviews; Type: TABLE DATA; Schema: public; Owner: news
--

COPY pageviews (pageviewid, articleid, ipaddress, datecreated) FROM stdin;
1	46	\N	2017-06-26 14:25:08.187+03
2	46	\N	2017-06-26 14:25:08.225+03
3	46	\N	2017-06-26 14:27:41.305+03
4	46	\N	2017-06-26 14:27:41.328+03
5	46	\N	2017-06-26 14:28:31.685+03
6	46	\N	2017-06-26 14:28:31.718+03
7	34	\N	2017-06-26 14:28:40.01+03
8	23	\N	2017-06-26 14:30:23.833+03
9	25	\N	2017-06-26 14:30:38.6+03
10	22	\N	2017-06-26 14:31:29.703+03
11	22	\N	2017-06-26 14:31:33.348+03
12	22	\N	2017-06-26 14:31:33.388+03
13	22	\N	2017-06-26 14:31:33.396+03
14	1	\N	2017-06-26 14:31:37.405+03
15	1	\N	2017-06-26 14:32:02.817+03
16	1	\N	2017-06-26 14:32:02.847+03
17	2	\N	2017-06-26 14:32:06.461+03
18	5	\N	2017-06-26 14:32:12.246+03
19	6	\N	2017-06-26 14:32:40.881+03
20	2	\N	2017-06-26 14:33:15.163+03
21	2	\N	2017-06-26 14:33:17.805+03
22	2	\N	2017-06-26 14:33:17.827+03
23	2	\N	2017-06-26 14:33:17.866+03
24	2	\N	2017-06-26 14:33:17.886+03
25	2	\N	2017-06-26 14:33:33.034+03
26	2	\N	2017-06-26 14:33:33.06+03
27	12	\N	2017-06-26 14:33:36.672+03
28	12	\N	2017-06-26 14:33:40.116+03
29	12	\N	2017-06-26 14:33:40.135+03
30	1	\N	2017-06-26 14:33:43.108+03
31	29	\N	2017-06-26 14:56:46.183+03
32	29	\N	2017-06-26 14:58:42.424+03
33	8	\N	2017-06-26 16:04:49.188+03
34	8	\N	2017-06-26 16:04:53.282+03
35	8	\N	2017-06-26 16:04:55.723+03
36	8	\N	2017-06-26 16:05:31.851+03
37	8	\N	2017-06-26 16:05:57.162+03
38	8	\N	2017-06-26 16:06:09.001+03
39	8	\N	2017-06-26 16:06:11.842+03
40	8	\N	2017-06-26 16:06:35.179+03
41	1	\N	2017-06-26 16:39:34.707+03
42	1	\N	2017-06-26 16:39:38.295+03
43	1	\N	2017-06-26 16:40:02.003+03
44	1	\N	2017-06-26 16:40:31.825+03
45	1	\N	2017-06-26 16:41:10.29+03
46	1	\N	2017-06-26 16:41:13.62+03
47	1	\N	2017-06-26 16:42:00.525+03
48	1	\N	2017-06-26 16:42:35.223+03
49	2	\N	2017-06-26 16:44:26.101+03
50	6	\N	2017-06-26 16:44:29.646+03
51	10	\N	2017-06-26 16:44:32.793+03
52	10	\N	2017-06-26 16:44:35.792+03
53	4	\N	2017-06-26 16:44:38.578+03
54	9	\N	2017-06-26 16:44:41.191+03
55	9	\N	2017-06-26 16:44:43.328+03
56	9	\N	2017-06-26 16:44:45.367+03
57	9	\N	2017-06-26 16:44:47.463+03
58	9	\N	2017-06-26 16:44:49.293+03
59	3	\N	2017-06-26 16:45:53.551+03
60	3	\N	2017-06-26 16:45:56.539+03
61	3	\N	2017-06-26 16:45:58.565+03
62	3	\N	2017-06-26 16:46:00.836+03
63	3	\N	2017-06-26 16:46:03.249+03
64	3	\N	2017-06-26 16:46:05.316+03
65	3	\N	2017-06-26 16:46:28.303+03
66	3	\N	2017-06-26 16:46:35.832+03
67	9	\N	2017-06-26 17:00:06.876+03
\.


--
-- Name: pageviews_pageviewid_seq; Type: SEQUENCE SET; Schema: public; Owner: news
--

SELECT pg_catalog.setval('pageviews_pageviewid_seq', 67, true);


--
-- Data for Name: status; Type: TABLE DATA; Schema: public; Owner: news
--

COPY status (statusid, description, datecreated, datemodified) FROM stdin;
1	Active Author	2017-06-25 17:40:47.03245+03	2017-06-25 17:40:47.03245+03
2	Active Article	2017-06-25 17:42:38.113632+03	2017-06-25 17:42:38.113632+03
\.


--
-- Name: status_statusid_seq; Type: SEQUENCE SET; Schema: public; Owner: news
--

SELECT pg_catalog.setval('status_statusid_seq', 2, true);


--
-- Name: articles_pkey; Type: CONSTRAINT; Schema: public; Owner: news
--

ALTER TABLE ONLY articles
    ADD CONSTRAINT articles_pkey PRIMARY KEY (articleid);


--
-- Name: authors_pkey; Type: CONSTRAINT; Schema: public; Owner: news
--

ALTER TABLE ONLY authors
    ADD CONSTRAINT authors_pkey PRIMARY KEY (authorid);


--
-- Name: authors_username_key; Type: CONSTRAINT; Schema: public; Owner: news
--

ALTER TABLE ONLY authors
    ADD CONSTRAINT authors_username_key UNIQUE (username);


--
-- Name: categories_pkey; Type: CONSTRAINT; Schema: public; Owner: news
--

ALTER TABLE ONLY categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (categoryid);


--
-- Name: configs_name_key; Type: CONSTRAINT; Schema: public; Owner: news
--

ALTER TABLE ONLY configs
    ADD CONSTRAINT configs_name_key UNIQUE (name);


--
-- Name: configs_pkey; Type: CONSTRAINT; Schema: public; Owner: news
--

ALTER TABLE ONLY configs
    ADD CONSTRAINT configs_pkey PRIMARY KEY (configid);


--
-- Name: pageviews_pkey; Type: CONSTRAINT; Schema: public; Owner: news
--

ALTER TABLE ONLY pageviews
    ADD CONSTRAINT pageviews_pkey PRIMARY KEY (pageviewid);


--
-- Name: status_pkey; Type: CONSTRAINT; Schema: public; Owner: news
--

ALTER TABLE ONLY status
    ADD CONSTRAINT status_pkey PRIMARY KEY (statusid);


--
-- Name: articles_authorid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: news
--

ALTER TABLE ONLY articles
    ADD CONSTRAINT articles_authorid_fkey FOREIGN KEY (authorid) REFERENCES authors(authorid);


--
-- Name: articles_categoryid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: news
--

ALTER TABLE ONLY articles
    ADD CONSTRAINT articles_categoryid_fkey FOREIGN KEY (categoryid) REFERENCES categories(categoryid);


--
-- Name: articles_statusid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: news
--

ALTER TABLE ONLY articles
    ADD CONSTRAINT articles_statusid_fkey FOREIGN KEY (statusid) REFERENCES status(statusid);


--
-- Name: authors_statusid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: news
--

ALTER TABLE ONLY authors
    ADD CONSTRAINT authors_statusid_fkey FOREIGN KEY (statusid) REFERENCES status(statusid);


--
-- Name: pageviews_articleid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: news
--

ALTER TABLE ONLY pageviews
    ADD CONSTRAINT pageviews_articleid_fkey FOREIGN KEY (articleid) REFERENCES articles(articleid);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

