PGDMP     0    5                y            staffer    13.1    13.1     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16647    staffer    DATABASE     T   CREATE DATABASE "staffer" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'C';
    DROP DATABASE "staffer";
                postgres    false            �          0    20570    Day 
   TABLE DATA           /   COPY "public"."Day" ("id", "date") FROM stdin;
    public          postgres    false    204   �       �          0    16650    Employee 
   TABLE DATA           u   COPY "public"."Employee" ("id", "firstName", "lastName", "email", "phone", "address", "enabled", "days") FROM stdin;
    public          postgres    false    201          �          0    16661    Position 
   TABLE DATA           [   COPY "public"."Position" ("id", "title", "employeeId", "startTime", "endTime") FROM stdin;
    public          postgres    false    203   -       �          0    20588    PositionDay 
   TABLE DATA           @   COPY "public"."PositionDay" ("positionId", "dayId") FROM stdin;
    public          postgres    false    208   J       �          0    25419    Shift 
   TABLE DATA           I   COPY "public"."Shift" ("id", "name", "startTime", "endTime") FROM stdin;
    public          postgres    false    212   g       �          0    25382    Week 
   TABLE DATA           @   COPY "public"."Week" ("id", "startDate", "endDate") FROM stdin;
    public          postgres    false    210   �                  0    0    Days_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('"public"."Days_id_seq"', 1, false);
          public          postgres    false    205                       0    0    Employee_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('"public"."Employee_id_seq"', 1, false);
          public          postgres    false    200                       0    0    PositionDays_dayId_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('"public"."PositionDays_dayId_seq"', 1, false);
          public          postgres    false    207                       0    0    PositionDays_positionId_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('"public"."PositionDays_positionId_seq"', 1, false);
          public          postgres    false    206                       0    0    Position_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('"public"."Position_id_seq"', 1, false);
          public          postgres    false    202                       0    0    Shift_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('"public"."Shift_id_seq"', 1, false);
          public          postgres    false    211            	           0    0    Week_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('"public"."Week_id_seq"', 1, false);
          public          postgres    false    209            �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �     