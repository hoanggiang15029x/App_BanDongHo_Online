USE [WebBanHang]
GO
/****** Object:  Table [dbo].[admin]    Script Date: 5/30/2021 7:31:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[admin](
	[tendn] [varchar](255) NOT NULL,
	[matkhau] [varchar](8) NOT NULL,
 CONSTRAINT [PK_admin] PRIMARY KEY CLUSTERED 
(
	[tendn] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[chitietdonhang]    Script Date: 5/30/2021 7:31:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[chitietdonhang](
	[id_dh] [int] NOT NULL,
	[id_sp] [int] NOT NULL,
	[soluong] [int] NOT NULL,
 CONSTRAINT [PK_chitietdonhang] PRIMARY KEY CLUSTERED 
(
	[id_dh] ASC,
	[id_sp] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[chitietphieunhap]    Script Date: 5/30/2021 7:31:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[chitietphieunhap](
	[id_pn] [int] NOT NULL,
	[id_sp] [int] NOT NULL,
	[soluong] [int] NOT NULL,
 CONSTRAINT [PK_chitietphieunhap] PRIMARY KEY CLUSTERED 
(
	[id_pn] ASC,
	[id_sp] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[danhgia]    Script Date: 5/30/2021 7:31:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[danhgia](
	[sdt] [varchar](10) NOT NULL,
	[id_sp] [int] NOT NULL,
	[vote] [int] NOT NULL,
 CONSTRAINT [PK_danhgia] PRIMARY KEY CLUSTERED 
(
	[sdt] ASC,
	[id_sp] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[donhang]    Script Date: 5/30/2021 7:31:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[donhang](
	[id_dh] [int] IDENTITY(1,1) NOT NULL,
	[ngaydathang] [datetime] NOT NULL,
	[sdt] [varchar](10) NOT NULL,
	[trangthai] [varchar](255) NOT NULL,
 CONSTRAINT [PK_donhang] PRIMARY KEY CLUSTERED 
(
	[id_dh] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[khachhang]    Script Date: 5/30/2021 7:31:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[khachhang](
	[sdt] [varchar](10) NOT NULL,
	[tenKH] [nvarchar](255) NOT NULL,
	[diaChi] [nvarchar](255) NOT NULL,
	[email] [varchar](255) NOT NULL,
	[matKhau] [varchar](8) NOT NULL,
	[dia_chi] [varchar](255) NULL,
	[mat_khau] [varchar](255) NULL,
 CONSTRAINT [PK_khachhang] PRIMARY KEY CLUSTERED 
(
	[sdt] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[kieusanpham]    Script Date: 5/30/2021 7:31:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[kieusanpham](
	[id_kieusp] [int] IDENTITY(1,1) NOT NULL,
	[tenkieu] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_kieusanpham] PRIMARY KEY CLUSTERED 
(
	[id_kieusp] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[loaisanpham]    Script Date: 5/30/2021 7:31:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[loaisanpham](
	[id_loaisp] [int] IDENTITY(1,1) NOT NULL,
	[tenloai] [nvarchar](255) NOT NULL,
 CONSTRAINT [PK_loaisanpham] PRIMARY KEY CLUSTERED 
(
	[id_loaisp] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[phieunhap]    Script Date: 5/30/2021 7:31:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[phieunhap](
	[id_pn] [int] IDENTITY(1,1) NOT NULL,
	[ngaynhap] [datetime] NOT NULL,
 CONSTRAINT [PK_phieunhap] PRIMARY KEY CLUSTERED 
(
	[id_pn] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[sanpham]    Script Date: 5/30/2021 7:31:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sanpham](
	[id_sp] [int] IDENTITY(1,1) NOT NULL,
	[tensp] [nvarchar](255) NOT NULL,
	[gia] [int] NOT NULL,
	[anhsp] [nvarchar](max) NULL,
	[mota] [nvarchar](max) NULL,
	[id_loaisp] [int] NOT NULL,
	[id_kieusp] [int] NULL,
	[soluongton] [int] NULL,
 CONSTRAINT [PK_sanpham] PRIMARY KEY CLUSTERED 
(
	[id_sp] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
INSERT [dbo].[khachhang] ([sdt], [tenKH], [diaChi], [email], [matKhau], [dia_chi], [mat_khau]) VALUES (N'0922967024', N'Giang', N'123', N'123', N'12345678', NULL, NULL)
SET IDENTITY_INSERT [dbo].[kieusanpham] ON 

INSERT [dbo].[kieusanpham] ([id_kieusp], [tenkieu]) VALUES (1, N'Đồng hồ nam')
INSERT [dbo].[kieusanpham] ([id_kieusp], [tenkieu]) VALUES (2, N'Đồng hồ nữ')
INSERT [dbo].[kieusanpham] ([id_kieusp], [tenkieu]) VALUES (3, N'Đồ hồ đôi')
INSERT [dbo].[kieusanpham] ([id_kieusp], [tenkieu]) VALUES (4, N'Đồ hồ trẻ em')
INSERT [dbo].[kieusanpham] ([id_kieusp], [tenkieu]) VALUES (5, N'Mắt kính nam')
INSERT [dbo].[kieusanpham] ([id_kieusp], [tenkieu]) VALUES (6, N'Mắt kính nữ')
INSERT [dbo].[kieusanpham] ([id_kieusp], [tenkieu]) VALUES (7, N'Gọng kính')
INSERT [dbo].[kieusanpham] ([id_kieusp], [tenkieu]) VALUES (8, N'Dây đồng hồ')
INSERT [dbo].[kieusanpham] ([id_kieusp], [tenkieu]) VALUES (9, N'Tủ xoay đồng hồ')
SET IDENTITY_INSERT [dbo].[kieusanpham] OFF
SET IDENTITY_INSERT [dbo].[loaisanpham] ON 

INSERT [dbo].[loaisanpham] ([id_loaisp], [tenloai]) VALUES (1, N'Đồng hồ')
INSERT [dbo].[loaisanpham] ([id_loaisp], [tenloai]) VALUES (2, N'Mắt kính')
INSERT [dbo].[loaisanpham] ([id_loaisp], [tenloai]) VALUES (3, N'Phụ kiện')
SET IDENTITY_INSERT [dbo].[loaisanpham] OFF
SET IDENTITY_INSERT [dbo].[sanpham] ON 

INSERT [dbo].[sanpham] ([id_sp], [tensp], [gia], [anhsp], [mota], [id_loaisp], [id_kieusp], [soluongton]) VALUES (1, N'Đồng hồ Jacques Lemans JL-11-1654.2ZD', 5399100, N'1565640287_donghonhapkhau15.jpg', NULL, 1, 1, 37)
INSERT [dbo].[sanpham] ([id_sp], [tensp], [gia], [anhsp], [mota], [id_loaisp], [id_kieusp], [soluongton]) VALUES (2, N'Đồng hồ Jacques Lemans JL-1-1945D', 6480000, N'712042116_donghonhapkhau105.jpg', NULL, 1, 1, 46)
INSERT [dbo].[sanpham] ([id_sp], [tensp], [gia], [anhsp], [mota], [id_loaisp], [id_kieusp], [soluongton]) VALUES (3, N'Đồng hồ Jacques Lemans 11-1654.2ZH', 6299100, N'857803036_donghonhapkhau22.jpg', NULL, 1, 1, 70)
INSERT [dbo].[sanpham] ([id_sp], [tensp], [gia], [anhsp], [mota], [id_loaisp], [id_kieusp], [soluongton]) VALUES (4, N'ĐỒNG HỒ EPOS SWISS E-3435.313.24.26.25', 50310000, N'784845121_donghothuysy13.jpg', NULL, 1, 1, 30)
INSERT [dbo].[sanpham] ([id_sp], [tensp], [gia], [anhsp], [mota], [id_loaisp], [id_kieusp], [soluongton]) VALUES (5, N'ĐỒNG HỒ PHILIPPE AUGUSTE PA-555.1', 17099100, N'72429618_1034985426_donghokimnguu2.jpg', NULL, 1, 1, 76)
INSERT [dbo].[sanpham] ([id_sp], [tensp], [gia], [anhsp], [mota], [id_loaisp], [id_kieusp], [soluongton]) VALUES (6, N'Đồng hồ Diamond D DM38445', 3258000, N'1334090572_donghothoitrang7.jpg', N'Thương hiệu: Đồng hồ nữ Diamond D

Kiểu dáng: Đồng hồ nữ

Máy: Quartz

Chất liệu vỏ: Hợp kim , đính đá swarovsky

Chất liệu dây: Hợp kim

Mặt kính: Sapphire ( Kính chống trầy )

Kích thước : 24mm

Chống nước : 3 ATM

Bảo hành: 10 năm về máy và đá , 2 năm về pin

Thương hiệu: Anh 

Gọi để được tư vấn và đặt hàng: 098.668.1189', 1, 2, 32)
INSERT [dbo].[sanpham] ([id_sp], [tensp], [gia], [anhsp], [mota], [id_loaisp], [id_kieusp], [soluongton]) VALUES (7, N'Đồng hồ Diamond D DM64205IG-B', 4815000, N'2007329083_donghonu3.jpg', N'Thương hiệu: Đồng hồ nữ Diamond D

Kiểu dáng: Đồng hồ nữ

Máy: Quartz

Chất liệu vỏ:Hợp kim mạ PVD , đính đá swarovsky

Chất liệu dây: dây da

Mặt kính: Sapphire ( Kính chống trầy )

Chống nước: 3 ATM

Kích thước: 33 mm

Bảo hành trọn đời về máy và đá,  2 năm về pin

Thương hiệu: Anh 

Máy: Japan Myota citizen Quartz', 1, 2, 56)
INSERT [dbo].[sanpham] ([id_sp], [tensp], [gia], [anhsp], [mota], [id_loaisp], [id_kieusp], [soluongton]) VALUES (9, N'Đồng hồ Diamond D DM61195IG-B', 6363000, N'1653747273_Dong-ho-Diamond-D-DM61195IG-B.jpg', N'Thương hiệu: Đồng hồ nữ Diamond D trẻ trung, thời trang, phong cách với mặt kính sapphire chống trầy, độ trong suốt cao.', 1, 2, 43)
INSERT [dbo].[sanpham] ([id_sp], [tensp], [gia], [anhsp], [mota], [id_loaisp], [id_kieusp], [soluongton]) VALUES (10, N'Kính mát Rayban RB-3025-112/17(58IT)', 5350000, N'753283163_kính-mắt-đăng-quang49.jpg', NULL, 2, 5, 24)
INSERT [dbo].[sanpham] ([id_sp], [tensp], [gia], [anhsp], [mota], [id_loaisp], [id_kieusp], [soluongton]) VALUES (11, N'Kính mát Rayban RB-3025-001/3E(58IT)', 5450000, N'921170800_kính-rayban169.jpg', NULL, 2, 5, 20)
INSERT [dbo].[sanpham] ([id_sp], [tensp], [gia], [anhsp], [mota], [id_loaisp], [id_kieusp], [soluongton]) VALUES (14, N'Kính RAYBAN RB-4278-6282/71(51IT)', 5250000, N'2091709855_kính-rayban308.jpg', NULL, 2, 6, 62)
INSERT [dbo].[sanpham] ([id_sp], [tensp], [gia], [anhsp], [mota], [id_loaisp], [id_kieusp], [soluongton]) VALUES (15, N'Kính mát PHILIPPE AUGUSTE PA-8913-53/C7', 3622500, N'1875400613_KINH-THOI-TRANG-18.jpg', NULL, 2, 6, 30)
INSERT [dbo].[sanpham] ([id_sp], [tensp], [gia], [anhsp], [mota], [id_loaisp], [id_kieusp], [soluongton]) VALUES (18, N'TỦ PHILIPPE AUGUSTE 6 NGĂN XOAY 7 NGĂN BÀY WW6-7/C', 6100000, N'990289388_hopdonghoco42.jpg', N'Có khóa tủ
Chế độ xoay của tủ :

Sử dụng nguồn điện 220v. Có 5 chế độ hoạt động cho khách hàng lựa chọn tùy thích gồm:
Chế độ 1: OFF.
Chế độ 2: Xoay theo chiều kim đồng hồ 2 phút - Nghỉ 6 phút rồi lặp lại.
Chế độ 3: Xoay ngược chiều kim đồng hồ 2 phút - Dừng 6 phút rồi lặp lại.
Chế độ 4: Là sự kết hợp của Chế độ 2 và Chế độ 3.
Chế độ 5: Xoay theo chiều kim đồng hồ 5 phút - Xoay ngược chiều kim đồng hồ 5 phút - Lặp lại trong 3 tiếng - Nghỉ 9 tiếng rồi lặp lại.
', 3, 9, 10)
INSERT [dbo].[sanpham] ([id_sp], [tensp], [gia], [anhsp], [mota], [id_loaisp], [id_kieusp], [soluongton]) VALUES (19, N'HỘP PHILIPPE AUGUSTE 1 NGĂN XOAY WW1-0/A', 1600000, N'1413898215_hopdonghoco20.jpg', N'Sử dụng nguồn điện 220v hoặc pin AA.
Có 5 chế độ hoạt động cho khách hàng lựa chọn tùy thích gồm:

Chế độ 1: OFF.
Chế độ 2: Xoay theo chiều kim đồng hồ 2 phút - Nghỉ 6 phút rồi lặp lại.
Chế độ 3: Xoay ngược chiều kim đồng hồ 2 phút - Dừng 6 phút rồi lặp lại.
Chế độ 4: Là sự kết hợp của Chế độ 2 và Chế độ 3.
Chế độ 5: Xoay theo chiều kim đồng hồ 5 phút - Xoay ngược chiều kim đồng hồ 5 phút - Lặp lại trong 3 tiếng - Nghỉ 9 tiếng rồi lặp lại.', 3, 9, 10)
INSERT [dbo].[sanpham] ([id_sp], [tensp], [gia], [anhsp], [mota], [id_loaisp], [id_kieusp], [soluongton]) VALUES (20, N'GỌNG KÍNH PHILIPPE AUGUSTE PA-ST16200-52/C4', 4485000, N'115826062_kinh-can29.jpg', NULL, 2, 7, 40)
INSERT [dbo].[sanpham] ([id_sp], [tensp], [gia], [anhsp], [mota], [id_loaisp], [id_kieusp], [soluongton]) VALUES (21, N'Gọng kính PHILIPPE AUGUSTE PA-8557T-53/C6', 1955000, N'1954584296_kinh thoi trang 12.jpg', NULL, 2, 7, 30)
SET IDENTITY_INSERT [dbo].[sanpham] OFF
ALTER TABLE [dbo].[sanpham] ADD  CONSTRAINT [DF__sanpham__mota__15502E78]  DEFAULT (NULL) FOR [mota]
GO
ALTER TABLE [dbo].[chitietdonhang]  WITH CHECK ADD  CONSTRAINT [FK_chitietdonhang_donhang] FOREIGN KEY([id_dh])
REFERENCES [dbo].[donhang] ([id_dh])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[chitietdonhang] CHECK CONSTRAINT [FK_chitietdonhang_donhang]
GO
ALTER TABLE [dbo].[chitietdonhang]  WITH CHECK ADD  CONSTRAINT [FK_chitietdonhang_sanpham] FOREIGN KEY([id_sp])
REFERENCES [dbo].[sanpham] ([id_sp])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[chitietdonhang] CHECK CONSTRAINT [FK_chitietdonhang_sanpham]
GO
ALTER TABLE [dbo].[chitietdonhang]  WITH CHECK ADD  CONSTRAINT [FKfqsavx16k0gktp1yyg8j11mxc] FOREIGN KEY([id_dh])
REFERENCES [dbo].[donhang] ([id_dh])
GO
ALTER TABLE [dbo].[chitietdonhang] CHECK CONSTRAINT [FKfqsavx16k0gktp1yyg8j11mxc]
GO
ALTER TABLE [dbo].[chitietdonhang]  WITH CHECK ADD  CONSTRAINT [FKpx6kyyljbnxe3me5lb5kg07gh] FOREIGN KEY([id_sp])
REFERENCES [dbo].[sanpham] ([id_sp])
GO
ALTER TABLE [dbo].[chitietdonhang] CHECK CONSTRAINT [FKpx6kyyljbnxe3me5lb5kg07gh]
GO
ALTER TABLE [dbo].[chitietphieunhap]  WITH CHECK ADD  CONSTRAINT [FK_chitietphieunhap_phieunhap] FOREIGN KEY([id_pn])
REFERENCES [dbo].[phieunhap] ([id_pn])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[chitietphieunhap] CHECK CONSTRAINT [FK_chitietphieunhap_phieunhap]
GO
ALTER TABLE [dbo].[chitietphieunhap]  WITH CHECK ADD  CONSTRAINT [FK_chitietphieunhap_sanpham] FOREIGN KEY([id_sp])
REFERENCES [dbo].[sanpham] ([id_sp])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[chitietphieunhap] CHECK CONSTRAINT [FK_chitietphieunhap_sanpham]
GO
ALTER TABLE [dbo].[chitietphieunhap]  WITH CHECK ADD  CONSTRAINT [FKcpvx95yopo8oxqkjac6c54v9b] FOREIGN KEY([id_sp])
REFERENCES [dbo].[sanpham] ([id_sp])
GO
ALTER TABLE [dbo].[chitietphieunhap] CHECK CONSTRAINT [FKcpvx95yopo8oxqkjac6c54v9b]
GO
ALTER TABLE [dbo].[chitietphieunhap]  WITH CHECK ADD  CONSTRAINT [FKcul4abkautjbg6nq9r23ghabi] FOREIGN KEY([id_pn])
REFERENCES [dbo].[phieunhap] ([id_pn])
GO
ALTER TABLE [dbo].[chitietphieunhap] CHECK CONSTRAINT [FKcul4abkautjbg6nq9r23ghabi]
GO
ALTER TABLE [dbo].[danhgia]  WITH CHECK ADD  CONSTRAINT [FK_danhgia_khachhang] FOREIGN KEY([sdt])
REFERENCES [dbo].[khachhang] ([sdt])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[danhgia] CHECK CONSTRAINT [FK_danhgia_khachhang]
GO
ALTER TABLE [dbo].[danhgia]  WITH CHECK ADD  CONSTRAINT [FK_danhgia_sanpham] FOREIGN KEY([id_sp])
REFERENCES [dbo].[sanpham] ([id_sp])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[danhgia] CHECK CONSTRAINT [FK_danhgia_sanpham]
GO
ALTER TABLE [dbo].[danhgia]  WITH CHECK ADD  CONSTRAINT [FKh26nurf5utk42sjsm3f4utfy5] FOREIGN KEY([sdt])
REFERENCES [dbo].[khachhang] ([sdt])
GO
ALTER TABLE [dbo].[danhgia] CHECK CONSTRAINT [FKh26nurf5utk42sjsm3f4utfy5]
GO
ALTER TABLE [dbo].[danhgia]  WITH CHECK ADD  CONSTRAINT [FKm96uhtory49gvlsume3vdieo0] FOREIGN KEY([id_sp])
REFERENCES [dbo].[sanpham] ([id_sp])
GO
ALTER TABLE [dbo].[danhgia] CHECK CONSTRAINT [FKm96uhtory49gvlsume3vdieo0]
GO
ALTER TABLE [dbo].[donhang]  WITH CHECK ADD  CONSTRAINT [FK_donhang_khachhang] FOREIGN KEY([sdt])
REFERENCES [dbo].[khachhang] ([sdt])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[donhang] CHECK CONSTRAINT [FK_donhang_khachhang]
GO
ALTER TABLE [dbo].[donhang]  WITH CHECK ADD  CONSTRAINT [FK9rmqt931swwac5tj9bclw17uv] FOREIGN KEY([sdt])
REFERENCES [dbo].[khachhang] ([sdt])
GO
ALTER TABLE [dbo].[donhang] CHECK CONSTRAINT [FK9rmqt931swwac5tj9bclw17uv]
GO
ALTER TABLE [dbo].[sanpham]  WITH CHECK ADD  CONSTRAINT [FK_sanpham_kieusanpham] FOREIGN KEY([id_kieusp])
REFERENCES [dbo].[kieusanpham] ([id_kieusp])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[sanpham] CHECK CONSTRAINT [FK_sanpham_kieusanpham]
GO
ALTER TABLE [dbo].[sanpham]  WITH CHECK ADD  CONSTRAINT [FK_sanpham_loaisanpham] FOREIGN KEY([id_loaisp])
REFERENCES [dbo].[loaisanpham] ([id_loaisp])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[sanpham] CHECK CONSTRAINT [FK_sanpham_loaisanpham]
GO
ALTER TABLE [dbo].[sanpham]  WITH CHECK ADD  CONSTRAINT [FK3jfeaibnfp8twbp4ygdd2cvnd] FOREIGN KEY([id_loaisp])
REFERENCES [dbo].[loaisanpham] ([id_loaisp])
GO
ALTER TABLE [dbo].[sanpham] CHECK CONSTRAINT [FK3jfeaibnfp8twbp4ygdd2cvnd]
GO
ALTER TABLE [dbo].[sanpham]  WITH CHECK ADD  CONSTRAINT [FKdgp2990i176jtodceb72yrion] FOREIGN KEY([id_kieusp])
REFERENCES [dbo].[kieusanpham] ([id_kieusp])
GO
ALTER TABLE [dbo].[sanpham] CHECK CONSTRAINT [FKdgp2990i176jtodceb72yrion]
GO
/****** Object:  Statistic [PK_admin]    Script Date: 5/30/2021 7:31:51 PM ******/
UPDATE STATISTICS [dbo].[admin]([PK_admin]) WITH STATS_STREAM = 0x0100000001000000000000000000000026B0C7EF0000000040000000000000000000000000000000A7030000A7000000FF0000000000000008D0003400000000, ROWCOUNT = 0, PAGECOUNT = 0
GO
/****** Object:  Statistic [PK_chitietdonhang]    Script Date: 5/30/2021 7:31:51 PM ******/
UPDATE STATISTICS [dbo].[chitietdonhang]([PK_chitietdonhang]) WITH STATS_STREAM = 0x01000000020000000000000000000000C71DA72C0000000058000000000000000000000000000000380300003800000004000A00000000000000000000000000380300003800000004000A00000000000000000060000000, ROWCOUNT = 0, PAGECOUNT = 0
GO
/****** Object:  Statistic [PK_chitietphieunhap]    Script Date: 5/30/2021 7:31:51 PM ******/
UPDATE STATISTICS [dbo].[chitietphieunhap]([PK_chitietphieunhap]) WITH STATS_STREAM = 0x01000000020000000000000000000000C71DA72C0000000058000000000000000000000000000000380300003800000004000A00000000000000000000000000380300003800000004000A00000000000000000060000000, ROWCOUNT = 0, PAGECOUNT = 0
GO
/****** Object:  Statistic [PK_danhgia]    Script Date: 5/30/2021 7:31:51 PM ******/
UPDATE STATISTICS [dbo].[danhgia]([PK_danhgia]) WITH STATS_STREAM = 0x01000000020000000000000000000000571297B90000000058000000000000000000000000000000A7030000A70000000A0000000000000008D000343800000038030A003800000004000A00000000000000000038000000, ROWCOUNT = 0, PAGECOUNT = 0
GO
/****** Object:  Statistic [PK_donhang]    Script Date: 5/30/2021 7:31:51 PM ******/
UPDATE STATISTICS [dbo].[donhang]([PK_donhang]) WITH STATS_STREAM = 0x010000000100000000000000000000000E23E3300000000040000000000000000000000000000000380300003800000004000A000000000000000000FFFFFFFF, ROWCOUNT = 0, PAGECOUNT = 0
GO
/****** Object:  Statistic [PK_khachhang]    Script Date: 5/30/2021 7:31:51 PM ******/
UPDATE STATISTICS [dbo].[khachhang]([PK_khachhang]) WITH STATS_STREAM = 0x010000000100000000000000000000001E82BE18000000002B02000000000000EB01000000000000A7030000A70000000A0000000000000008D0003400740061070000005E3BE10038AD000001000000000000000100000000000000000000000000803F000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001000000010000000100000010000000000020410000803F0000000000002041000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000013040000000000000000000000000000290000000000000067000000000000006F0000000000000077000000000000000800000000000000300010000000803F000000000000803F0400000100210030393232393637303234FF01000000000000000100000001000000280000002800000000000000000000000A00000030393232393637303234020000004000000000010A0000000001000000000000000000000000000000, ROWCOUNT = 1, PAGECOUNT = 1
GO
/****** Object:  Statistic [_WA_Sys_00000002_2E1BDC42]    Script Date: 5/30/2021 7:31:51 PM ******/
CREATE STATISTICS [_WA_Sys_00000002_2E1BDC42] ON [dbo].[kieusanpham]([tenkieu]) WITH STATS_STREAM = 0x01000000010000000000000000000000905C43BD000000002302000000000000E301000000000000E7030000E7000000640000000000000008D00034930100000700000018C9D50036AD000001000000000000000100000000000000000000000000803F0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010000000100000001000000100000000000C0400000803F000000000000C04000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001304000000000000000000000000000025000000000000005F0000000000000067000000000000006F000000000000000800000000000000300010000000803F000000000000803F04000001001D004E0061006D00FF0100000000000000010000000100000028000000280000000000000000000000030000004E0061006D0002000000400000000001030000000001000000000000000000000000000000
GO
/****** Object:  Statistic [PK_kieusanpham]    Script Date: 5/30/2021 7:31:51 PM ******/
UPDATE STATISTICS [dbo].[kieusanpham]([PK_kieusanpham]) WITH STATS_STREAM = 0x0100000001000000000000000000000052678174000000001902000000000000D901000000000000380300003800000004000A00000000000000000000000000070000003044D80036AD0000040000000000000004000000000000000000803F0000803E000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000003000000030000000100000014000000000080400000804000000000000080400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000110400000000000000000000000000005D0000000000000065000000000000006D0000000000000018000000000000002F000000000000004600000000000000100014000000803F000000000000803F01000000040000100014000000803F0000803F0000803F03000000040000100014000000803F000000000000803F0400000004000004000000000000000000000000000000, ROWCOUNT = 9, PAGECOUNT = 1
GO
/****** Object:  Statistic [_WA_Sys_00000002_3F466844]    Script Date: 5/30/2021 7:31:51 PM ******/
CREATE STATISTICS [_WA_Sys_00000002_3F466844] ON [dbo].[loaisanpham]([tenloai]) WITH STATS_STREAM = 0x010000000100000000000000000000007045CE3C000000008C020000000000004C02000000000000E7030000E7000000FE0100000000000008D000340000803F07000000329FD50036AD0000030000000000000003000000000000000000803FABAAAA3E000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000002000000020000000100000010000000555575410000404000000000555575410000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000130400000000000000000000000000005C00000000000000C800000000000000D000000000000000D80000000000000010000000000000003500000000000000300010000000803F000000000000803F040000010025001001D31E6E00670020006800D31E300010000000803F0000803F0000803F0400000100270050006800E51E20006B006900C71E6E00FF0100000000000000030000000300000028000000280000000000000000000000170000001001D31E6E00670020006800D31E4D00AF1E740020006B00ED006E00680050006800E51E20006B006900C71E6E000400000040000000008107000000810807000001080F00000003000000000000000000000000000000
GO
/****** Object:  Statistic [PK_loaisanpham]    Script Date: 5/30/2021 7:31:51 PM ******/
UPDATE STATISTICS [dbo].[loaisanpham]([PK_loaisanpham]) WITH STATS_STREAM = 0x010000000100000000000000000000007A48D25100000000FA01000000000000BA01000000000000380300003800000004000A00000000000000000000000000070000004D71D50036AD000002000000000000000200000000000000000000000000003F000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000002000000020000000100000014000000000080400000004000000000000080400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000110400000000000000000000000000003E0000000000000046000000000000004E0000000000000010000000000000002700000000000000100014000000803F000000000000803F01000000040000100014000000803F000000000000803F0200000004000002000000000000000000000000000000, ROWCOUNT = 3, PAGECOUNT = 1
GO
/****** Object:  Statistic [PK_phieunhap]    Script Date: 5/30/2021 7:31:51 PM ******/
UPDATE STATISTICS [dbo].[phieunhap]([PK_phieunhap]) WITH STATS_STREAM = 0x01000000010000000000000000000000ED0358EE0000000040000000000000000000000000000000380300003800000004000A00000000000000000000000000, ROWCOUNT = 0, PAGECOUNT = 0
GO
/****** Object:  Statistic [_WA_Sys_00000006_5165187F]    Script Date: 5/30/2021 7:31:51 PM ******/
CREATE STATISTICS [_WA_Sys_00000006_5165187F] ON [dbo].[sanpham]([id_loaisp]) WITH STATS_STREAM = 0x01000000010000000000000000000000EBB65F53000000001902000000000000D901000000000000380300613800000004000A0000000000000000000001000007000000C8D9E20036AD00001300000000000000130000000000000000000000ABAAAA3E000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000003000000030000000100000014000000000080400000984100000000000080400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000110400000000000000000000000000005D0000000000000065000000000000006D0000000000000018000000000000002F0000000000000046000000000000001000140000000041000000000000803F01000000040000100014000000E040000000000000803F020000000400001000140000008040000000000000803F0300000004000013000000000000000000000000000000
GO
/****** Object:  Statistic [_WA_Sys_00000007_5165187F]    Script Date: 5/30/2021 7:31:51 PM ******/
CREATE STATISTICS [_WA_Sys_00000007_5165187F] ON [dbo].[sanpham]([id_kieusp]) WITH STATS_STREAM = 0x01000000010000000000000000000000924088AE0000000076020000000000003602000000000000380200003800000004000A0000000000000000000000000007000000C7D9E20036AD0000130000000000000013000000000000000000003F2549123E00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000600000006000000010000001400000000008040000098410000000000008040000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011040000000000000000000000000000BA00000000000000C200000000000000CA00000000000000300000000000000047000000000000005E0000000000000075000000000000008C00000000000000A300000000000000100014000000A040000000000000803F010000000400001000140000004040000000000000803F020000000400001000140000000040000000000000803F050000000400001000140000004040000000000000803F0600000004000010001400000000400000004000000040080000000400001000140000000040000000000000803F0900000004000013000000000000000000000000000000
GO
/****** Object:  Statistic [PK_sanpham]    Script Date: 5/30/2021 7:31:51 PM ******/
UPDATE STATISTICS [dbo].[sanpham]([PK_sanpham]) WITH STATS_STREAM = 0x01000000010000000000000000000000C508320F00000000D3020000000000009302000000000000380300003800000004000A0000000000000000000100000007000000BED9E20036AD0000130000000000000013000000000000000000803F3694573D0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000090000000900000001000000140000000000804000009841000000000000804000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001104000000000000000000000000000017010000000000001F01000000000000270100000000000048000000000000005F0000000000000076000000000000008D00000000000000A400000000000000BB00000000000000D200000000000000E9000000000000000001000000000000100014000000803F000000000000803F01000000040000100014000000803F0000803F0000803F03000000040000100014000000803F0000803F0000803F05000000040000100014000000803F0000803F0000803F07000000040000100014000000803F000000400000803F0B000000040000100014000000803F000000400000803F0F000000040000100014000000803F0000803F0000803F11000000040000100014000000803F0000803F0000803F13000000040000100014000000803F0000803F0000803F1500000004000013000000000000000000000000000000, ROWCOUNT = 16, PAGECOUNT = 2
GO
