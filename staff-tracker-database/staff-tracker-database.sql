PGDMP     *                    y           staffer    13.1    13.1                 0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16647    staffer    DATABASE     T   CREATE DATABASE "staffer" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'C';
    DROP DATABASE "staffer";
                postgres    false            �          0    20570    Day 
   TABLE DATA           9   COPY "public"."Day" ("id", "date", "weekId") FROM stdin;
    public          postgres    false    204   �       �          0    16650    Employee 
   TABLE DATA           m   COPY "public"."Employee" ("id", "firstName", "lastName", "email", "phone", "address", "enabled") FROM stdin;
    public          postgres    false    201   6       �          0    16661    Position 
   TABLE DATA           d   COPY "public"."Position" ("id", "title", "startTime", "endTime", "employeeId", "shift") FROM stdin;
    public          postgres    false    203   Y       �          0    20588    PositionDay 
   TABLE DATA           @   COPY "public"."PositionDay" ("positionId", "dayId") FROM stdin;
    public          postgres    false    208   0       �          0    25419    Shift 
   TABLE DATA           I   COPY "public"."Shift" ("id", "name", "startTime", "endTime") FROM stdin;
    public          postgres    false    212          �          0    25382    Week 
   TABLE DATA           @   COPY "public"."Week" ("id", "startDate", "endDate") FROM stdin;
    public          postgres    false    210   �                  0    0    Day_weekId_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('"public"."Day_weekId_seq"', 1, false);
          public          postgres    false    213                       0    0    Days_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('"public"."Days_id_seq"', 139, true);
          public          postgres    false    205                       0    0    Employee_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('"public"."Employee_id_seq"', 21, true);
          public          postgres    false    200                       0    0    PositionDays_dayId_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('"public"."PositionDays_dayId_seq"', 1, false);
          public          postgres    false    207                       0    0    PositionDays_positionId_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('"public"."PositionDays_positionId_seq"', 1, false);
          public          postgres    false    206                       0    0    Position_employeeId_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('"public"."Position_employeeId_seq"', 1, true);
          public          postgres    false    214                       0    0    Position_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('"public"."Position_id_seq"', 49, true);
          public          postgres    false    202                       0    0    Shift_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('"public"."Shift_id_seq"', 1, false);
          public          postgres    false    211                       0    0    Week_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('"public"."Week_id_seq"', 8, true);
          public          postgres    false    209            �   u   x�MϹ1DQ[�2[: ��?�Ә��s(�<l����썶���gv�_��~g����y���-̀] �S� /0@�'9�p!>�B���P.��,Q-��l�y���X�:.      �     x�e�=O�0���+<R������QE����RK��.���	���;=wo	/<��1lwM��8�x(:7�V��:�+)�������G���5��l�Z��b�a�ت�:����0*mfP� ��\4���KJގ�6���F�!:;k�B�;=#��n<.���F ��l�d�ܛ���}��Ҷ�H�mo0݂n���0]�fx-H;��m�����"��(x��s�.\����]9Y&��9M�3��vD�#y�Œ<#���3�YdY��v~�      �   �   x�}���0��ۧ�	�� W5��r��ńƘ( >������m�vfg��XW���f�l>����ݍ����]��tm�Ղ�~������-ˉ<������+�K sx�s�� R��d'�XFDl&�C{W�L0E�K��W_���pQ��x�W�� �*|{A��?��Gq�b��5i�"6�i�f�� R��Y      �   ?   x�%˻ !�Кs"?������5�c�
QE���W7��&r��J1��ąO��������      �      x������ � �      �   -   x�3�4202�50�54D0͹�������`�p��qqq �     