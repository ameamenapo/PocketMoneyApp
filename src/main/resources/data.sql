/* rolesテーブルINSERT */
INSERT IGNORE INTO roles (role_id, role_name) VALUES
  (1, 'ROLE_GENERAL'),
  (2, 'ROLE_ADMIN');


/* ユーザーマスタINSERT */
/* パスワードは両方password */
INSERT IGNORE INTO user_mst (user_id, password, role_id)
     VALUES ('admin', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO',2);
INSERT IGNORE INTO user_mst (user_id, password, role_id)
     VALUES ('test', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO',1);


/* 口座マスタINSERT */
INSERT IGNORE INTO bank_account_mst (account_id, user_id, financial_asset)
     VALUES ('1', 'admin', '1000');
INSERT IGNORE INTO bank_account_mst (account_id, user_id, financial_asset)
     VALUES ('2', 'test', '1000');


/* お手伝いリストINSERT */
INSERT IGNORE INTO help_mst (help_id, help_name, help_ammount)
     VALUES ('1', '洗い物', '100');
INSERT IGNORE INTO help_mst (help_id, help_name, help_ammount)
     VALUES ('2', 'お風呂掃除', '50');
INSERT IGNORE INTO help_mst (help_id, help_name, help_ammount)
     VALUES ('3', '猫トイレ掃除', '10');
INSERT IGNORE INTO help_mst (help_id, help_name, help_ammount)
     VALUES ('4', '料理のお手伝い', '50');



