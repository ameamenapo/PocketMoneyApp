/*--allo_userで実行する（テーブル作成）パスワードはallo-----------------*/

/* ロールマスタ */
CREATE TABLE roles (
  role_id     INT         NOT NULL,
  role_name   VARCHAR(50) NOT NULL,
  PRIMARY KEY(role_id)
);

/* ユーザーマスタ作成 */
CREATE TABLE user_mst (
  user_id     VARCHAR(16)  NOT NULL,
  password    VARCHAR(100)  NOT NULL,
  role_id     INT         NOT NULL,
  PRIMARY KEY(user_id),
  FOREIGN KEY(role_id) REFERENCES roles (role_id)
);


/* 口座マスタ作成 */
CREATE TABLE bank_account_mst (
  account_id   INT   NOT NULL AUTO_INCREMENT,
  user_id   VARCHAR(16) NOT NULL,
  financial_asset    INT NOT NULL,
  PRIMARY KEY(account_id),
  FOREIGN KEY(user_id) REFERENCES user_mst (user_id)
);

/* お手伝いマスタ作成 */
CREATE TABLE help_mst (
  help_id       INT          NOT NULL AUTO_INCREMENT,
  help_name     VARCHAR(32)  NOT NULL,
  help_ammount  INT          NOT NULL,
  PRIMARY KEY(help_id)
);


/* ユーザーお手伝いリスト作成 */
CREATE TABLE user_help_list (
  user_help_id  INT          NOT NULL AUTO_INCREMENT,
  help_id       INT          NOT NULL,
  help_name     VARCHAR(32)  NOT NULL,
  help_ammount  INT          NOT NULL,
  user_id       VARCHAR(16)  NOT NULL,
  reported_flag  INT(1)      NOT NULL,
  PRIMARY KEY(user_help_id),
  FOREIGN KEY(user_id) REFERENCES user_mst(user_id)
);

/* 報告されたお手伝いリスト作成 */
CREATE TABLE reported_help_list (
  reported_help_id   INT          NOT NULL AUTO_INCREMENT,
  help_id            INT          NOT NULL,
  help_name          VARCHAR(32)  NOT NULL,
  help_ammount       INT          NOT NULL,
  user_id            VARCHAR(16)  NOT NULL,
  approved_flag      INT(1)       NOT NULL,
  PRIMARY KEY(reported_help_id),
  FOREIGN KEY(user_id) REFERENCES user_mst(user_id)
);

/* 承認したお手伝いリスト作成 */
CREATE TABLE approved_help_list (
  approved_help_id  INT          NOT NULL AUTO_INCREMENT,
  reported_help_id  INT          NOT NULL,
  help_id           INT          NOT NULL,
  help_name         VARCHAR(32)  NOT NULL,
  help_ammount      INT          NOT NULL,
  user_id           VARCHAR(16)  NOT NULL,
  PRIMARY KEY(approved_help_id),
  FOREIGN KEY(user_id) REFERENCES user_mst(user_id)
);

/* 差戻しお手伝いリスト作成 */
CREATE TABLE not_approved_help_list (
  not_approved_help_id   INT          NOT NULL AUTO_INCREMENT,
  reported_help_id       INT          NOT NULL,
  help_id                INT          NOT NULL,
  help_name              VARCHAR(32)  NOT NULL,
  help_ammount           INT          NOT NULL,
  user_id                VARCHAR(16)  NOT NULL,
  reported_flag          INT(1)       NOT NULL,
  PRIMARY KEY(not_approved_help_id),
  FOREIGN KEY(user_id) REFERENCES user_mst(user_id)
);

/* ユーザーイメージマスタ作成 */
CREATE TABLE user_image_mst (
  image_id   BIGINT   NOT NULL AUTO_INCREMENT,
  user_id VARCHAR(16) NOT NULL,
  image_name   VARCHAR(32),
  image_data   LONGBLOB,
  image_type   VARCHAR(32),
  PRIMARY KEY(image_id),
  FOREIGN KEY(user_id) REFERENCES user_mst (user_id)
);

/* プロフィールマスタ作成 */
CREATE TABLE profile_mst (
  profile_id     INT   NOT NULL AUTO_INCREMENT,
  user_id        VARCHAR(16) NOT NULL,
  profile_name   VARCHAR(32),
  birth_year     CHAR(4),
  birth_month    CHAR(2),
  birth_day      CHAR(2),
  saving_goal    VARCHAR(32),
  what_i_want    VARCHAR(32),
  comments       VARCHAR(200),
  PRIMARY KEY(profile_id),
  FOREIGN KEY(user_id) REFERENCES user_mst (user_id)
);