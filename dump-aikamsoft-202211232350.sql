--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2
-- Dumped by pg_dump version 14.2

-- Started on 2022-11-23 23:50:39

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 33565)
-- Name: aikam; Type: SCHEMA; Schema: -; Owner: admin
--

CREATE SCHEMA aikam;


ALTER SCHEMA aikam OWNER TO admin;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 211 (class 1259 OID 33574)
-- Name: customers; Type: TABLE; Schema: aikam; Owner: admin
--

CREATE TABLE aikam.customers (
    id bigint NOT NULL,
    name character varying NOT NULL,
    surname character varying NOT NULL
);


ALTER TABLE aikam.customers OWNER TO admin;

--
-- TOC entry 210 (class 1259 OID 33573)
-- Name: customers_id_seq; Type: SEQUENCE; Schema: aikam; Owner: admin
--

ALTER TABLE aikam.customers ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME aikam.customers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 213 (class 1259 OID 33580)
-- Name: products; Type: TABLE; Schema: aikam; Owner: admin
--

CREATE TABLE aikam.products (
    id bigint NOT NULL,
    name character varying NOT NULL,
    price numeric NOT NULL
);


ALTER TABLE aikam.products OWNER TO admin;

--
-- TOC entry 212 (class 1259 OID 33579)
-- Name: products_id_seq; Type: SEQUENCE; Schema: aikam; Owner: admin
--

ALTER TABLE aikam.products ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME aikam.products_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 214 (class 1259 OID 33613)
-- Name: purchases; Type: TABLE; Schema: aikam; Owner: admin
--

CREATE TABLE aikam.purchases (
    customer_id bigint NOT NULL,
    product_id bigint NOT NULL,
    date timestamp without time zone NOT NULL
);


ALTER TABLE aikam.purchases OWNER TO admin;

--
-- TOC entry 3320 (class 0 OID 33574)
-- Dependencies: 211
-- Data for Name: customers; Type: TABLE DATA; Schema: aikam; Owner: admin
--

COPY aikam.customers (id, name, surname) FROM stdin;
2	Николай	Петров
3	Сергей	Иванов
4	Петр	Николаев
5	Алексей	Алексеев
6	Мария	Петрова
7	Анжелика	Наумова
8	Анна	Сергеева
9	Антон	Марков
10	Иван	Крутиков
11	Олег	Крутиков
1	Арсений	Иванов
\.


--
-- TOC entry 3322 (class 0 OID 33580)
-- Dependencies: 213
-- Data for Name: products; Type: TABLE DATA; Schema: aikam; Owner: admin
--

COPY aikam.products (id, name, price) FROM stdin;
1	Вода	100
2	Шоколад	30
3	Варенье	35.50
4	Хлеб	29.90
5	Картофель	35.54
6	Лук	29.98
\.


--
-- TOC entry 3323 (class 0 OID 33613)
-- Dependencies: 214
-- Data for Name: purchases; Type: TABLE DATA; Schema: aikam; Owner: admin
--

COPY aikam.purchases (customer_id, product_id, date) FROM stdin;
1	1	2022-11-18 00:00:00
2	1	2022-11-19 00:00:00
1	2	2022-11-18 00:00:00
1	3	2022-11-18 00:00:00
3	1	2022-11-20 00:00:00
5	1	2022-11-17 00:00:00
4	2	2022-11-16 00:00:00
6	5	2022-11-15 00:00:00
7	4	2022-11-14 00:00:00
8	2	2022-11-13 00:00:00
9	5	2022-11-12 00:00:00
10	6	2022-11-11 00:00:00
3	2	2022-11-20 00:00:00
3	3	2022-11-20 00:00:00
3	1	2022-11-21 00:00:00
3	2	2022-11-21 00:00:00
3	3	2022-11-21 00:00:00
3	3	2022-11-20 00:00:00
\.


--
-- TOC entry 3329 (class 0 OID 0)
-- Dependencies: 210
-- Name: customers_id_seq; Type: SEQUENCE SET; Schema: aikam; Owner: admin
--

SELECT pg_catalog.setval('aikam.customers_id_seq', 11, true);


--
-- TOC entry 3330 (class 0 OID 0)
-- Dependencies: 212
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: aikam; Owner: admin
--

SELECT pg_catalog.setval('aikam.products_id_seq', 6, true);


--
-- TOC entry 3175 (class 2606 OID 33589)
-- Name: customers customers_pk; Type: CONSTRAINT; Schema: aikam; Owner: admin
--

ALTER TABLE ONLY aikam.customers
    ADD CONSTRAINT customers_pk PRIMARY KEY (id);


--
-- TOC entry 3177 (class 2606 OID 33591)
-- Name: products products_pk; Type: CONSTRAINT; Schema: aikam; Owner: admin
--

ALTER TABLE ONLY aikam.products
    ADD CONSTRAINT products_pk PRIMARY KEY (id);


--
-- TOC entry 3178 (class 2606 OID 33627)
-- Name: purchases purchases_fk; Type: FK CONSTRAINT; Schema: aikam; Owner: admin
--

ALTER TABLE ONLY aikam.purchases
    ADD CONSTRAINT purchases_fk FOREIGN KEY (customer_id) REFERENCES aikam.customers(id);


--
-- TOC entry 3179 (class 2606 OID 33632)
-- Name: purchases purchases_fk_1; Type: FK CONSTRAINT; Schema: aikam; Owner: admin
--

ALTER TABLE ONLY aikam.purchases
    ADD CONSTRAINT purchases_fk_1 FOREIGN KEY (product_id) REFERENCES aikam.products(id);


-- Completed on 2022-11-23 23:50:39

--
-- PostgreSQL database dump complete
--

