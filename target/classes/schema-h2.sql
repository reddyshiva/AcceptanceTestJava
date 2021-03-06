CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 1 INCREMENT BY 1;
CREATE TABLE USERS
        (
        USER_ID VARCHAR2(50) NOT NULL,
        USER_NAME VARCHAR2(50) NOT NULL,
        MOBILE_NUMBER VARCHAR2(50)  NULL,
        EMAIL_ID VARCHAR2(50)  NULL,
        ACTIVATION_STATUS VARCHAR2(5) DEFAULT false,
        BIRTH_DATE date  NULL,
        PRIMARY KEY(USER_ID)
        );
CREATE TABLE DEPENDENTS
        (
        USER_ID VARCHAR2(50) NOT NULL,
        DEPENDENT_USER_ID VARCHAR2(50) NOT NULL,
        USER_NAME VARCHAR2(50) NOT NULL,
        MOBILE_NUMBER VARCHAR2(50)  NULL,
        BIRTH_DATE date  NULL,
        PRIMARY KEY(DEPENDENT_USER_ID)
        );
        
        

INSERT INTO USERS (USER_ID, USER_NAME, MOBILE_NUMBER,EMAIL_ID,ACTIVATION_STATUS,BIRTH_DATE) VALUES
  ('12345', 'kiran', '9740501902','test@gmail.com','true',TO_DATE('21/06/1990', 'MM/DD/YYYY')),
  ('12346', 'shiva', '9740501903','test1@gmail.com','false',TO_DATE('21/06/1990', 'MM/DD/YYYY')),
   ('12347', 'shiva', '9740501903','test1@gmail.com','false',TO_DATE('21/06/1990', 'MM/DD/YYYY'));
  
INSERT INTO DEPENDENTS (USER_ID,DEPENDENT_USER_ID, USER_NAME, MOBILE_NUMBER,BIRTH_DATE) VALUES
  ('12345', '34553425','test user1', '9740501905',TO_DATE('21/06/1990', 'MM/DD/YYYY')),
  ('12346', '34543232','test user2', '9740501906',TO_DATE('21/06/1990', 'MM/DD/YYYY')), 
   ('12346', '345421312','test user11', '9740501906',TO_DATE('21/06/1990', 'MM/DD/YYYY')),
   ('12345', '34553426','test user3', '9740501905',TO_DATE('21/06/1990', 'MM/DD/YYYY')),
    ('12345', '34553427','test user4', '9740501905',TO_DATE('21/06/1990', 'MM/DD/YYYY')), 
       ('12347', '34552429','test user8', '9740501905',TO_DATE('21/06/1990', 'MM/DD/YYYY'));  
    
    commit;      

  