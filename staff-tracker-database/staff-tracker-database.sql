PGDMP         *                y           staffer    13.1    13.1                 0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
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
   TABLE DATA           d   COPY "public"."Position" ("id", "title", "employeeId", "shift", "startTime", "endTime") FROM stdin;
    public          postgres    false    203   �       �          0    20588    PositionDay 
   TABLE DATA           @   COPY "public"."PositionDay" ("positionId", "dayId") FROM stdin;
    public          postgres    false    208   �       �          0    25419    Shift 
   TABLE DATA           I   COPY "public"."Shift" ("id", "name", "startTime", "endTime") FROM stdin;
    public          postgres    false    212   �       �          0    25382    Week 
   TABLE DATA           @   COPY "public"."Week" ("id", "startDate", "endDate") FROM stdin;
    public          postgres    false    210   �                  0    0    Day_weekId_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('"public"."Day_weekId_seq"', 1, false);
          public          postgres    false    213                       0    0    Days_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('"public"."Days_id_seq"', 139, true);
          public          postgres    false    205                       0    0    Employee_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('"public"."Employee_id_seq"', 22, true);
          public          postgres    false    200                       0    0    PositionDays_dayId_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('"public"."PositionDays_dayId_seq"', 1, false);
          public          postgres    false    207                       0    0    PositionDays_positionId_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('"public"."PositionDays_positionId_seq"', 1, false);
          public          postgres    false    206                       0    0    Position_employeeId_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('"public"."Position_employeeId_seq"', 1, true);
          public          postgres    false    214                       0    0    Position_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('"public"."Position_id_seq"', 49, true);
          public          postgres    false    202                       0    0    Shift_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('"public"."Shift_id_seq"', 1, false);
          public          postgres    false    211                       0    0    Week_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('"public"."Week_id_seq"', 8, true);
          public          postgres    false    209            �   u   x�MϹ1DQ[�2[: ��?�Ә��s(�<l����썶���gv�_��~g����y���-̀] �S� /0@�'9�p!>�B���P.��,Q-��l�y���X�:.      �   E  x�e�=o�0���7�D���Hi�ԂT�.&9��c#Ǵ��אVB�˽�=��1x9��ے=�f��s��q<H)�(+��JL��t>�qN6xepR\����5�QKG����K��L\:���"���*ʤ̋2Ʊ�6�Da���e[��?.���6��J�������:��ͩ\�DVy�(���l4�獶���1x�{M��]�B �'d�pVI�L�B��j��y,m[Mod[C�/h���f�D���(%���jo{j&#덂u8_�7�u��j��Wd��E��)8n��H��O>��+Q�=�� ~�������      �   �   x���A�0E��Sx�Li��7��-C�@�x~+m��X�5y���gH�Ew/�A�V�U}���m8o�ְ-���K#�'8V�jwkS�
 :5�a���(帀C�x� �������D�i��wP��-�����&y��QJQ��t��P���71bj��H���_���bi��?�E����W(Nw.����ˆ2�l�N-ۜE�N2�?�x��1���I      �   ;   x�ʹ�0�XW�G�$��_��� ���&�أo仯.y`b�����{�I?ō�      �      x������ � �      �   -   x�3�4202�50�54D0͹�������`�p��qqq �     