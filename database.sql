use master
drop database QuanLyXeBus


create database QuanLyXeBus
go
use QuanLyXeBus
go

CREATE TABLE DonViQuanLy(
	MaDonVi int not null IDENTITY(1,1),
	TenDonVI nvarchar(50) not null,
	SoDienThoai char(10) not null,
	DiaChi nvarchar(100) not null,
	Email nvarchar(50) not null,
	CONSTRAINT PK_DonViQuanLy PRIMARY KEY (MaDonVi),
	CONSTRAINT CHECK_DONVI_EMAIL CHECK (Email like '%_@_%._%'),
	CONSTRAINT CHECK_DONVI_SDT CHECK (SoDienThoai not like '%[^0-9]%' and len(SoDienThoai) = 10),
	CONSTRAINT CHECK_DONVI_TEN CHECK (len(TenDonVI) > 0),
	CONSTRAINT CHECK_DONVI_DiaChi CHECK (len(DiaChi) > 0),
	CONSTRAINT UNIQUE_DONVI_EMAIL Unique (Email),
	CONSTRAINT UNIQUE_DONVI_SDT Unique (SoDienThoai),
)

CREATE TABLE LoaiTuyen(
	MaLoaiTuyen int not null IDENTITY(1,1),
	TenLoaiTuyen nvarchar(50) not null,
	LoaiHinhHoatDong bit not null,
	GiaVeThuong money not null,
	GiaVeSinhVien money not null,
	GiaVeTap money not null,
	
	CONSTRAINT CHECK_LOAITUYEN_Ten CHECK (len(TenLoaiTuyen) > 0),
	CONSTRAINT UNIQUE_GiAVE UNIQUE(GiaVeThuong,GiaVeSinhVien,GiaVeTap),
	CONSTRAINT CHECK_LOAITUYEN_giave CHECK (giaVeThuong % 500 = 0 and giaVeTap % 500 = 0 and giaVeSinhVien % 500 = 0),
	CONSTRAINT CHECK_LOAITUYEN_GiaVeKhongAm CHECK (giaVeThuong >= 0 and giaVeTap >= 0 and giaVeSinhVien >= 0),
	CONSTRAINT PK_LoaiTuyen PRIMARY KEY (MaLoaiTuyen)
);


CREATE TABLE ChiTietTuyen(
	MaSo smallint not null,
	TenTuyen nvarchar(50) not null,
	CONSTRAINT PK_ChiTietTuyen PRIMARY KEY (MaSo),
	CONSTRAINT CHECK_ChiTietTuyen_Maso check(MaSo > 0),
	CONSTRAINT CHECK_ChiTietTuyen_TEN check(len(TenTuyen) > 0)
);

CREATE TABLE BieuDoGio(
	MaBieuDo int not null IDENTITY(1,1),
	ThoiGianBatDau time not null,
	ThoiGianKetThuc time not null,
	ThoiGianChuyen tinyint not null,
	GianCachChuyen tinyint not null,
	CONSTRAINT PK_BieuDoGio PRIMARY KEY (MaBieuDo),
	constraint gianCachToiThieu check(GianCachChuyen > 0 and GianCachChuyen <= 30),
	constraint CHECK_BieuDoGio_ThoiGianChuyen check (ThoiGianChuyen > 0),
	constraint ThoiGianHoatDongToiThieu check(DATEDIFF(HOUR, thoiGianBatDau, thoiGianKetThuc) >= 12)
);

CREATE TABLE Tuyen(
	MaTuyen int not null IDENTITY(1,1),
	MaSo smallint not null,
	NgayBatDauHoatDong date not null,
	NgayNgungHoatDong date not null,
	MaBieuDoGio int not null,
	MaLoaiTuyen int not null,
	MaDonViQuanLy int not null,

	CONSTRAINT PK_Tuyen PRIMARY KEY (MaTuyen),
	CONSTRAINT Unique_Maso_Ngay Unique (MaSo,NgayBatDauHoatDong,NgayNgungHoatDong),	
	constraint check_BatDau_KetThuc check(NgayBatDauHoatDong < NgayNgungHoatDong),

	CONSTRAINT FK_Tuyen_DonViQuanLy FOREIGN KEY (MaDonViQuanly)
    REFERENCES DonViQuanly(MaDonVi),
	CONSTRAINT FK_Tuyen_LoaiTuyen FOREIGN KEY (MaLoaiTuyen)
    REFERENCES LoaiTuyen(MaLoaiTuyen),
	CONSTRAINT FK_Tuyen_ChiTietTuyen FOREIGN KEY (MaSo)
    REFERENCES ChiTietTuyen(MaSo),
	CONSTRAINT FK_Tuyen_BieuDoGio FOREIGN KEY (MaBieuDoGio)
    REFERENCES BieuDoGio(MaBieuDo),
);

Create table DoanhThu(
	Ngay date not null,
	MaTuyen int not null,
	soVeThuong smallint not null default 0,
	soVeSinhVien smallint not null default 0,
	soVeTap smallint not null default 0,
	CONSTRAINT PK_DoanhThu PRIMARY KEY (Ngay,MaTuyen),
	CONSTRAINT Check_soVeLonHon0 check (soVeThuong >= 0 and soVeSinhVien >= 0 and soVeTap >= 0),
	CONSTRAINT FK_DoanhThu_Tuyen FOREIGN KEY (MaTuyen)
    REFERENCES Tuyen(MaTuyen)
)

CREATE TABLE Tram(
	MaTram int not null IDENTITY(1,1),
	TenTram nvarchar(20) not null,
	DiaChi nvarchar(100) not null,
	X float not null,
	Y float not null,

	CONSTRAINT PK_Tram PRIMARY KEY (MaTram),
	CONSTRAINT CHECK_TRAM_TEN check(len(TenTram) > 0),
	CONSTRAINT CHECK_DiaChi_TEN check(len(DiaChi) > 0)
);

CREATE TABLE LoTrinh(
	MaTram int not null,
	MaSo smallint not null,
	SoThuTu smallint not null,
	
	CONSTRAINT PK_TuyenQuaTram PRIMARY KEY (MaTram,MaSo),

	Constraint Unique_Tuyen_SoThuTu unique(MaSo,SoThuTu),
	constraint CHECK_LOTRINH_SOTHUTU check(SoThuTu >= 0),
	CONSTRAINT FK_TuyenQuaTram_Tram FOREIGN KEY (MaTram)
    REFERENCES Tram(MaTram),
	CONSTRAINT FK_TuyenQuaTram_Tuyen FOREIGN KEY (MaSo)
    REFERENCES ChiTietTuyen(MaSo)
);

CREATE TABLE NhaSanXuatXe(
	MaNhaSanXuat int not null IDENTITY(1,1),
	TenNhaSanXuat nvarchar(50) not null,
	SoDienThoai varchar(10) not null,
	DiaChi nvarchar(100) not null,
	Email varchar(50) not null,
	CONSTRAINT PK_NhaSanXuatXe PRIMARY KEY (MaNhaSanXuat),
	CONSTRAINT CHECK_NSX_EMAIL CHECK (Email like '%_@_%._%'),
	CONSTRAINT CHECK_NSX_SDT CHECK (SoDienThoai not like '%[^0-9]%' and len(SoDienThoai) = 10),
	CONSTRAINT CHECK_NSX_TEN CHECK (len(TenNhaSanXuat) > 0),
	CONSTRAINT CHECK_NSX_DiaChi CHECK (len(DiaChi) > 0),
	CONSTRAINT UNIQUE_NhaSanXuat_EMAIL Unique (Email),
	CONSTRAINT UNIQUE_NhaSanXuat_SDT Unique (SoDienThoai),
);


CREATE TABLE LoaiXe (
    MaLoaiXe int not null IDENTITY(1,1),
	TenLoaiXe nvarchar(50) not null,
    SoChoNgoi tinyint not null,
    SoChoDung tinyint not null,
	MayBanVe bit not null,
	KhoangCachDiChuyen smallint not null,
	TocDoToiDa smallint not null,
	CONSTRAINT PK_LoaiXe PRIMARY KEY (MaLoaiXe),
	CONSTRAINT check_TenLoaiXe Check (len(TenLoaiXe) > 0),
	CONSTRAINT check_SoChoNgoi Check (SoChoNgoi > 0),
	CONSTRAINT check_SoChoDung Check (SoChoDung > 0),
	CONSTRAINT check_KhoangCachDiChuyen Check (KhoangCachDiChuyen > 0),
	CONSTRAINT check_TocDoToiDa Check (TocDoToiDa > 0)

);

CREATE TABLE Xe(
	MaXe int not null IDENTITY(1,1),
	BienSoXe varchar(10) not null,
	HanBaoHanh date not null,
	MaNhaSanXuat int not null,
	MaLoaiXe int not null,

	CONSTRAINT PK_Xe PRIMARY KEY (MaXe),

	CONSTRAINT FK_Xe_NhaSanXuatXe FOREIGN KEY (MaNhaSanXuat)
    REFERENCES NhaSanXuatXe(MaNhaSanXuat),
	CONSTRAINT FK_Xe_LoaiXe FOREIGN KEY (MaLoaiXe)
    REFERENCES LoaiXe(MaLoaiXe),
	constraint BienSoXe_UNIQUE unique(BienSoXe)
);

CREATE TABLE PhanCong(
	MaXe int not null,
	MaTuyen int not null,
	chieu bit not null,
	chuyen tinyint not null,
	
	CONSTRAINT PK_PhanCong PRIMARY KEY (MaXe,MaTuyen),
	constraint UNIQUE_PhanCong unique(MaTuyen,chuyen,chieu),

	CONSTRAINT FK_PhanCong_Xe FOREIGN KEY (MaXe)
    REFERENCES Xe(MaXe),
	CONSTRAINT FK_PhanCong_Tuyen FOREIGN KEY (MaTuyen)
    REFERENCES Tuyen(MaTuyen),	
);


CREATE TABLE NhanVien(
	MaNhanVien int not null IDENTITY(1,1),
	CMND varchar(12) not null,
	Ho nvarchar(10) not null,
	Ten nvarchar(20) not null,
	GioiTinh bit not null,
	SoDienThoai nvarchar(10) not null,
	DiaChi nvarchar(100) not null,
	Email nvarchar(50) not null,
	NgaySinh Date not null,	
	MaDonVi int,

	CONSTRAINT PK_NhanVien PRIMARY KEY (MaNhanVien),
	
	CONSTRAINT FK_NhanVien_DonViQuanLy FOREIGN KEY (MaDonVi)
    REFERENCES DonViQuanLy(MaDonVi),
	CONSTRAINT CHECK_NSX_EMAIL CHECK (Email like '%_@_%._%'),
	CONSTRAINT CHECK_NHANVIEN_SDT CHECK (SoDienThoai not like '%[^0-9]%' and len(SoDienThoai) = 10),
	CONSTRAINT CHECK_NHANVIEN_CMND CHECK (SoDienThoai not like '%[^0-9]%' and (len(CMND) = 10 or len(CMND) = 12)),
	CONSTRAINT CHECK_NHANVIEN_TEN CHECK (len(ten) > 0),
	CONSTRAINT CHECK_NHANVIEN_Ho CHECK (len(ho) > 0),
	CONSTRAINT CHECK_NHANVIEN_DiaChi CHECK (len(DiaChi) > 0),
	constraint UNIQUE_NHANVIEN_CMND unique(cmnd),
	CONSTRAINT UNIQUE_NHANVIEN_EMAIL Unique (Email),
	CONSTRAINT UNIQUE_NHANVIEN_SDT Unique (SoDienThoai),

);

CREATE TABLE TaiKhoan(
	MaNhanVien int not null,
	TenDangNhap varchar(20) not null,
	MatKhau varchar(20) not null,
	CONSTRAINT PK_TaiKhoan PRIMARY KEY (MaNhanVien),
	CONSTRAINT CHECK_TAIKHOAN_TEN CHECK (len(TenDangNhap) > 0),
	CONSTRAINT CHECK_TAIKHOAN_Ho CHECK (len(MatKhau) > 0),
	CONSTRAINT FK_TaiKhoan_NhanVien FOREIGN KEY (MaNhanVien)
    REFERENCES NhanVien(MaNhanVien),
	constraint TenDangNhap_UNIQUE unique(TenDangNhap)
);

insert into NhanVien
(CMND, Ho, Ten, GioiTinh, SoDienThoai, DiaChi, Email, NgaySinh, MaDonVi, TrangThaiLamViec) 
values ('0123456789','do','khang',1,'0123456789','97 man thien','dokhang@gmail.com','2000-05-29',NULL,1)
insert into TaiKhoan(MaNhanVien, TenDangNhap, MatKhau)
values (1, 'admin', 'admin')

go;


CREATE PROCEDURE swapXe
@maXe1 int,
@maXe2 int,
@maTuyen int
AS
--need to store the original values
SELECT
    *,CASE WHEN MaXe=@maXe1 then @maXe2 ELSE @maXe1 END AS JoinId
    INTO #Temp
    FROM PhanCong
    WHERE MaXe in (@maXe1,@maXe2) and MaTuyen = @maTuyen
--swap values
UPDATE y
    SET y.chieu=t.chieu
        ,y.chuyen=t.chuyen
    FROM PhanCong  y
        INNER JOIN #Temp  t ON y.MaXe =t.JoinId
    WHERE y.MaXe in (@maXe1,@maXe2) and y.MaTuyen = @maTuyen
drop table #Temp
go;
