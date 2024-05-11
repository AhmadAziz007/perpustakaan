PGDMP  $                    |            perpustakaan    16.2 (Debian 16.2-1.pgdg120+2)    16.1     3           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            4           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            5           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            6           1262    73732    perpustakaan    DATABASE     w   CREATE DATABASE perpustakaan WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';
    DROP DATABASE perpustakaan;
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                pg_database_owner    false            7           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   pg_database_owner    false    4            �            1259    73776    mst_buku    TABLE     �   CREATE TABLE public.mst_buku (
    buku_id bigint NOT NULL,
    judul character varying(20),
    jumlah_stock integer,
    kode_buku character varying(20),
    pengarang character varying(20),
    tahun_penerbit timestamp(6) without time zone
);
    DROP TABLE public.mst_buku;
       public         heap    postgres    false    4            �            1259    73774    mst_buku_seq    SEQUENCE     v   CREATE SEQUENCE public.mst_buku_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.mst_buku_seq;
       public          postgres    false    4            �            1259    73757    mst_role    TABLE     �   CREATE TABLE public.mst_role (
    role_id bigint NOT NULL,
    role_name character varying(255),
    token character varying(255)
);
    DROP TABLE public.mst_role;
       public         heap    postgres    false    4            �            1259    73750    mst_role_seq    SEQUENCE     v   CREATE SEQUENCE public.mst_role_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.mst_role_seq;
       public          postgres    false    4            �            1259    73781    mst_transaksi    TABLE     V  CREATE TABLE public.mst_transaksi (
    transaksi_id bigint NOT NULL,
    buku_id bigint,
    jumlah integer,
    keterangan character varying(20),
    no_tiket character varying(255),
    status character varying(255),
    tgl_pengembalian timestamp(6) without time zone,
    tgl_pinjam timestamp(6) without time zone,
    user_id bigint
);
 !   DROP TABLE public.mst_transaksi;
       public         heap    postgres    false    4            �            1259    73775    mst_transaksi_seq    SEQUENCE     {   CREATE SEQUENCE public.mst_transaksi_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.mst_transaksi_seq;
       public          postgres    false    4            �            1259    73762    mst_user    TABLE     �   CREATE TABLE public.mst_user (
    user_id bigint NOT NULL,
    password character varying(255),
    role_id bigint,
    token character varying(255),
    user_email character varying(255),
    user_name character varying(255)
);
    DROP TABLE public.mst_user;
       public         heap    postgres    false    4            �            1259    73751    mst_user_seq    SEQUENCE     v   CREATE SEQUENCE public.mst_user_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.mst_user_seq;
       public          postgres    false    4            /          0    73776    mst_buku 
   TABLE DATA           f   COPY public.mst_buku (buku_id, judul, jumlah_stock, kode_buku, pengarang, tahun_penerbit) FROM stdin;
    public          postgres    false    221   �       +          0    73757    mst_role 
   TABLE DATA           =   COPY public.mst_role (role_id, role_name, token) FROM stdin;
    public          postgres    false    217   :       0          0    73781    mst_transaksi 
   TABLE DATA           �   COPY public.mst_transaksi (transaksi_id, buku_id, jumlah, keterangan, no_tiket, status, tgl_pengembalian, tgl_pinjam, user_id) FROM stdin;
    public          postgres    false    222   w       ,          0    73762    mst_user 
   TABLE DATA           \   COPY public.mst_user (user_id, password, role_id, token, user_email, user_name) FROM stdin;
    public          postgres    false    218   "       8           0    0    mst_buku_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.mst_buku_seq', 51, true);
          public          postgres    false    219            9           0    0    mst_role_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.mst_role_seq', 151, true);
          public          postgres    false    215            :           0    0    mst_transaksi_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.mst_transaksi_seq', 201, true);
          public          postgres    false    220            ;           0    0    mst_user_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.mst_user_seq', 201, true);
          public          postgres    false    216            �           2606    73780    mst_buku mst_buku_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.mst_buku
    ADD CONSTRAINT mst_buku_pkey PRIMARY KEY (buku_id);
 @   ALTER TABLE ONLY public.mst_buku DROP CONSTRAINT mst_buku_pkey;
       public            postgres    false    221            �           2606    73761    mst_role mst_role_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.mst_role
    ADD CONSTRAINT mst_role_pkey PRIMARY KEY (role_id);
 @   ALTER TABLE ONLY public.mst_role DROP CONSTRAINT mst_role_pkey;
       public            postgres    false    217            �           2606    73787     mst_transaksi mst_transaksi_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.mst_transaksi
    ADD CONSTRAINT mst_transaksi_pkey PRIMARY KEY (transaksi_id);
 J   ALTER TABLE ONLY public.mst_transaksi DROP CONSTRAINT mst_transaksi_pkey;
       public            postgres    false    222            �           2606    73768    mst_user mst_user_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.mst_user
    ADD CONSTRAINT mst_user_pkey PRIMARY KEY (user_id);
 @   ALTER TABLE ONLY public.mst_user DROP CONSTRAINT mst_user_pkey;
       public            postgres    false    218            /   c   x�3��N��.�)-�44�642�.�T�-ͩL���4204�54�50Q00�20 ".#΀��������"NC�`#cN�Ҍ��̼��"� ��b���� �^�      +   -   x�3�tL���S0���240�r� \cN������"?F��� 3V      0   �   x��ϱ
�0���}���r��M��"B��Kqh����`R��������:��=��a\�G �J�pBb�,�*<�c~�J:�U6ǎ�����ӔC��-6��(]�n�����t�w�E�a�G��d���񻾭��H�{�Ĥ�pB| _6N�      ,   G  x����n�@ ��5<�O@`vE%�(��kbB�;"���ŮڝI�rN�1D*p1��H'"�Q�,d� ��
,�tB�H����f���c$RK�W{��wA3�Ug|آ2U%l�dV�lڰrx�9�`3���"��]�k�B\��,�w%�>�}�3�3?�Vc�pS��v+����9x64d�:r}��c�JDT���?�*�%���*mW����y�G��}�C�2E��{����w��Z�>M�;pZ����EA�`�5(>�~v2��#,��j�l��Uک[>�Φ�W��@����+|�Vʒ[�E$.G������_7�O!/I��zi�     