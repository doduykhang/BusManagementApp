--khong the sua loai tuyen neu co trong doanh thu
create trigger TR_UPDATE_LOAITUYEN on LoaiTuyen
for update
not for replication
as
begin
	DECLARE @count int
	SET @count = (select count(inserted.MaLoaiTuyen) 
				from inserted,Tuyen,DoanhThu
				where Tuyen.MaLoaiTuyen = inserted.MaLoaiTuyen
						and DoanhThu.MaTuyen = Tuyen.MaTuyen
						)
	if(@count > 0)
		rollback transaction
end
go;

--khong the sua neu co tuyen chay bieu do nay
create trigger TR_UPDATE_BieuDoGio on BieuDoGio
for update
not for replication
as
begin
	DECLARE @count int
	SET @count = (select count(inserted.MaBieuDo) 
				from inserted,Tuyen,PhanCong
				where Tuyen.MaBieuDoGio = inserted.MaBieuDo
						and PhanCong.MaTuyen = Tuyen.MaTuyen
						)
	DECLARE @count2 int
	SET @count2 = (select count(inserted.MaBieuDo) 
				from inserted,Tuyen,DoanhThu
				where Tuyen.MaLoaiTuyen = inserted.MaBieuDo
						and DoanhThu.MaTuyen = Tuyen.MaTuyen
						)
	if(@count > 0 or @count2 > 0)
		rollback transaction
end
go;

--tuyen cung ma so khong trung thoi gian hoat dong
create trigger TR_UPDATE_Tuyen on Tuyen
for insert,update
not for replication
as
begin
	DECLARE @StartTime date
	DECLARE @EndTime date
	DECLARE @MaSo smallint
	DECLARE @count smallint

	set @StartTime = (select NgayBatDauHoatDong from inserted)
	set @EndTime = (select NgayNgungHoatDong from inserted)
	set @MaSo = (select MaSo from inserted)

	set @count = (select count(MaTuyen) 
				from Tuyen 
				where MaSo = @MaSo and 
				@StartTime <= NgayNgungHoatDong and 
				@EndTime >= NgayBatDauHoatDong)

	if(@count > 1)
		rollback transaction
		
end

--khong the sua tuyen da co trong doanh thu hoac phan cong
create trigger TR_CANUPDATE_Tuyen on Tuyen
for update
not for replication
as
begin
	DECLARE @count smallint
	set @count = (select count(Tuyen.MaTuyen) 
				from Tuyen, DoanhThu
				where DoanhThu.MaTuyen = Tuyen.MaTuyen)

	DECLARE @count2 smallint
	set @count2 = (select count(Tuyen.MaTuyen) 
				from Tuyen, PhanCong
				where PhanCong.MaTuyen = Tuyen.MaTuyen)
	if(@count > 0)
		rollback transaction
		
end

--chi co the them doanh thu trong thoi gian hoat dong cua tuyen va khong qua ngay hom nay

create trigger TR_Insert_DoanhThu on DoanhThu
for insert
not for replication
as
begin
	DECLARE @Date date
	DECLARE @MaTuyen int
	DECLARE @count smallint

	set @Date = (select ngay from inserted)
	if(@Date > GETDATE())
		rollback transaction
	set @MaTuyen = (select MaTuyen from inserted)
	
	

	set @count = (select count(MaTuyen) 
				from Tuyen 
				where MaTuyen = @MaTuyen and 
				@Date between NgayBatDauHoatDong and NgayNgungHoatDong)
	
	if(@count = 0)
		rollback transaction
end

--khong the them doanh thu neu tuyen chua co xe chay
create trigger TR_Insert_DoanhThu2 on DoanhThu
for insert
not for replication
as
begin
	DECLARE @count smallint
	set @count = (select count(PhanCong.MaTuyen) 
				from inserted,PhanCong
				where inserted.MaTuyen = PhanCong.MaTuyen
				)
	
	if(@count = 0)
		rollback transaction
end

--khong the cap nhat id cua doanh thu
create trigger TR_PREVENt_UPDATE_ID_DoanhThu on DoanhThu
for update
not for replication
as
begin
	IF UPDATE(ngay) or UPDATE(Matuyen)
		rollback transaction
end

--khong the xoa doanh thu
create trigger TR_PREVENT_DELTE_DoanhThu on DoanhThu
for delete
not for replication
as
begin
		rollback transaction
end

--khong the sua loai xe da co xe dc phan cong
create trigger TR_PREVENT_UPDATE_LOAIXE on LOAIXE
for update
not for replication
as
begin
	
	declare @count int
	set @count = (select count(inserted.MaLoaiXe) 
			from xe,inserted,PhanCong
			where xe.MaLoaiXe = inserted.MaLoaiXe and 
				Xe.MaXe = PhanCong.MaXe)
	if(@count > 0)
		rollback transaction
end

--khong the sua xe da  dc phan cong
create trigger TR_PREVENT_UPDATE_XE on Xe
for update
not for replication
as
begin
	
	declare @count int
	set @count = (select count(inserted.MaLoaiXe) 
			from inserted,PhanCong
			where inserted.MaXe = PhanCong.MaXe)
	if(@count > 0)
		rollback transaction
end

--chuyen phai phu hop voi bieu do gio
drop trigger TR_UPDATE_PHANCONG
create trigger TR_UPDATE_PHANCONG on PhanCong
for update,insert
not for replication
as
begin
	DECLARE @thoiGianChuyen float
	DECLARE @gianCachChuyen float
	DECLARE @soXe int
	DECLARE @chuyen int
	set @thoiGianChuyen = (select BieuDoGio.ThoiGianChuyen
							from Tuyen,inserted,BieuDoGio
							where Tuyen.MaTuyen = inserted.MaTuyen
							and Tuyen.MaBieuDoGio = BieuDoGio.MaBieuDo)
	set @gianCachChuyen = (select BieuDoGio.GianCachChuyen
							from Tuyen,inserted,BieuDoGio
							where Tuyen.MaTuyen = inserted.MaTuyen
							and Tuyen.MaBieuDoGio = BieuDoGio.MaBieuDo)

	set @soXe = (select CEILING(@thoiGianChuyen/@gianCachChuyen))

	set @chuyen = (select chuyen from inserted)	
	
	if(@chuyen > @soXe - 1)
		rollback transaction
end

--xe khong trung thoi gian
create trigger TR_INSERT_PhanCong on PhanCong
for insert
not for replication
as
begin
	
	
	DECLARE @StartTime date
	DECLARE @EndTime date
	DECLARE @MaXe int
	DECLARE @count int

	set @StartTime = (SELECT Tuyen.NgayBatDauHoatDong
					FROM Tuyen,inserted
					WHERE Tuyen.MaTuyen = inserted.MaTuyen)

	set @EndTime = (SELECT Tuyen.NgayNgungHoatDong
					FROM Tuyen,inserted
					WHERE Tuyen.MaTuyen = inserted.MaTuyen)

	set @MaXe = (SELECT MaXe
				FROM inserted)
					

	set @count = (select count(Tuyen.MaTuyen) 
				from Tuyen,PhanCong 
				where  
				@StartTime <= Tuyen.NgayNgungHoatDong and 
				@EndTime >= Tuyen.NgayBatDauHoatDong and
				Tuyen.MaTuyen = PhanCong.MaTuyen and
				PhanCong.MaXe = @MaXe
				)
	
	if(@count > 1)
		rollback transaction
end

--khong the xoa neu co doanh thu
create trigger TR_DELETE_PhanCong on PhanCong
for delete,update
not for replication
as
begin
	DECLARE @count int

				
	set @count = (select count(inserted.MaTuyen)
					from inserted,DoanhThu
					where inserted.MaTuyen = DoanhThu.MaTuyen
				)
	
	if(@count > 1)
		rollback transaction
end

--khong the xoa tai khoan

create trigger TR_DELETE_taikhoang on taikhoan
for delete
not for replication
as
begin
	rollback transaction
end