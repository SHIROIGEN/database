USE [DouBanDB]
GO
/****** Object:  Table [dbo].[Actor]    Script Date: 12/30/2018 17:24:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Actor](
	[ActorName] [varchar](20) NOT NULL,
 CONSTRAINT [PK_Actor] PRIMARY KEY CLUSTERED 
(
	[ActorName] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Moive]    Script Date: 12/30/2018 17:24:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Moive](
	[MovieName] [varchar](40) NOT NULL,
	[Director] [varchar](40) NULL,
	[Editor] [varchar](40) NULL,
	[Area] [varchar](20) NULL,
	[Language] [varchar](20) NULL,
	[Time] [smallint] NULL,
	[Date] [date] NULL,
	[Type] [varchar](50) NULL,
	[Introduction] [varchar](2048) NULL,
 CONSTRAINT [PK__Moive__F5C0AEA4014935CB] PRIMARY KEY CLUSTERED 
(
	[MovieName] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[UserName]    Script Date: 12/30/2018 17:24:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[UserName](
	[UserN] [varchar](50) NULL,
	[UserId] [bigint] NOT NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[TheUser]    Script Date: 12/30/2018 17:24:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TheUser](
	[UserId] [bigint] NOT NULL,
	[Passwd] [varchar](32) NULL,
 CONSTRAINT [PK_TheUser] PRIMARY KEY CLUSTERED 
(
	[UserId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Score]    Script Date: 12/30/2018 17:24:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Score](
	[UserId] [bigint] NULL,
	[MovieName] [varchar](40) NULL,
	[MovieScore] [float] NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Cinecism]    Script Date: 12/30/2018 17:24:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Cinecism](
	[MovieName] [varchar](40) NULL,
	[Cinecism] [varchar](512) NULL,
	[UserId] [bigint] NULL,
	[Date] [date] NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Actor_Movie]    Script Date: 12/30/2018 17:24:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Actor_Movie](
	[Actor] [varchar](20) NULL,
	[MovieName] [varchar](40) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  ForeignKey [FK_Actor_Movie_Actor]    Script Date: 12/30/2018 17:24:14 ******/
ALTER TABLE [dbo].[Actor_Movie]  WITH CHECK ADD  CONSTRAINT [FK_Actor_Movie_Actor] FOREIGN KEY([Actor])
REFERENCES [dbo].[Actor] ([ActorName])
GO
ALTER TABLE [dbo].[Actor_Movie] CHECK CONSTRAINT [FK_Actor_Movie_Actor]
GO
/****** Object:  ForeignKey [FK_Actor_Movie_Moive]    Script Date: 12/30/2018 17:24:14 ******/
ALTER TABLE [dbo].[Actor_Movie]  WITH CHECK ADD  CONSTRAINT [FK_Actor_Movie_Moive] FOREIGN KEY([MovieName])
REFERENCES [dbo].[Moive] ([MovieName])
GO
ALTER TABLE [dbo].[Actor_Movie] CHECK CONSTRAINT [FK_Actor_Movie_Moive]
GO
/****** Object:  ForeignKey [FK_Cinecism_Moive]    Script Date: 12/30/2018 17:24:14 ******/
ALTER TABLE [dbo].[Cinecism]  WITH CHECK ADD  CONSTRAINT [FK_Cinecism_Moive] FOREIGN KEY([MovieName])
REFERENCES [dbo].[Moive] ([MovieName])
GO
ALTER TABLE [dbo].[Cinecism] CHECK CONSTRAINT [FK_Cinecism_Moive]
GO
/****** Object:  ForeignKey [FK_Cinecism_TheUser]    Script Date: 12/30/2018 17:24:14 ******/
ALTER TABLE [dbo].[Cinecism]  WITH CHECK ADD  CONSTRAINT [FK_Cinecism_TheUser] FOREIGN KEY([UserId])
REFERENCES [dbo].[TheUser] ([UserId])
GO
ALTER TABLE [dbo].[Cinecism] CHECK CONSTRAINT [FK_Cinecism_TheUser]
GO
/****** Object:  ForeignKey [FK_Score_Moive]    Script Date: 12/30/2018 17:24:14 ******/
ALTER TABLE [dbo].[Score]  WITH CHECK ADD  CONSTRAINT [FK_Score_Moive] FOREIGN KEY([MovieName])
REFERENCES [dbo].[Moive] ([MovieName])
GO
ALTER TABLE [dbo].[Score] CHECK CONSTRAINT [FK_Score_Moive]
GO
/****** Object:  ForeignKey [FK_Score_TheUser]    Script Date: 12/30/2018 17:24:14 ******/
ALTER TABLE [dbo].[Score]  WITH CHECK ADD  CONSTRAINT [FK_Score_TheUser] FOREIGN KEY([UserId])
REFERENCES [dbo].[TheUser] ([UserId])
GO
ALTER TABLE [dbo].[Score] CHECK CONSTRAINT [FK_Score_TheUser]
GO
