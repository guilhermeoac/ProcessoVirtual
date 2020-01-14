drop table if exists tb_aluno;
drop table if exists tb_disciplina;
drop table if exists tb_matricula;
drop sequence if exists sq_aluno;
drop sequence if exists sq_disciplina;
drop sequence if exists sq_matricula;

CREATE SEQUENCE sq_aluno minvalue 1
maxvalue 9999999999
start with 1
increment by 1
nocache
cycle;

CREATE TABLE tb_aluno
(
    id_aluno         NUMBER(19,0) NOT NULL,
    ds_nome           VARCHAR(100) NOT NULL,
    ds_sexo          CHAR(1)  NOT NULL,
    ds_email         VARCHAR (100),
    ds_telefone      VARCHAR(11),
    ds_cpf           VARCHAR (11),
    CONSTRAINT aluno_pk PRIMARY KEY (id_aluno)

);

CREATE SEQUENCE sq_disciplina minvalue 1
maxvalue 9999999999
start with 1
increment by 1
nocache
cycle;

CREATE TABLE tb_disciplina
(
    id_disciplina NUMBER(19,0) NOT NULL,
    ds_nome       VARCHAR(100) NOT NULL,
    nu_limite_alunos NUMBER(19,0) NOT NULL,
    nu_carga_horaria NUMBER(19,0),
    CONSTRAINT disciplina_pk PRIMARY KEY (id_disciplina)
);
CREATE SEQUENCE sq_matricula minvalue 1
maxvalue 9999999999
start with 1
increment by 1
nocache
cycle;

CREATE TABLE tb_matricula
(
    id_matricula NUMBER(19,0) NOT NULL,
    id_aluno  NUMBER(19,0) NOT NULL,
    id_disciplina NUMBER(19,0) NOT NULL,
    dh_registro       TIMESTAMP NOT NULL,
    CONSTRAINT matricula_pk PRIMARY KEY (id_matricula),
    CONSTRAINT aluno_fk
    FOREIGN KEY (id_aluno)
    REFERENCES tb_aluno(id_aluno),
    CONSTRAINT disciplina_fk
    FOREIGN KEY (id_disciplina)
    REFERENCES tb_disciplina(id_disciplina)
);

