create database TH_Tuan5;
use TH_Tuan5;

create table LichGiangDay (
	STT int auto_increment primary key,
    TenGV nvarchar(50),
    MaLop varchar(30),
    TenMon nvarchar (50),
    SiSo int,
    BatDau Date,
    KetThuc Date,
    SoTiet int
);

insert into LichGiangDay(TenGV, MaLop, TenMon, SiSo, BatDau, KetThuc, SoTiet)
values ('Phan Nguyệt Minh', 'SE405.I21', 'Mobile and Pervasive Computing', 94, '2018-01-15', '2018-05-26', 80)

