CREATE TABLE tarefa (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descricao VARCHAR(255),
    status VARCHAR(255) NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ultima_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    usuario_id BIGINT NOT NULL,
    prioridade VARCHAR(255),
    prazo DATETIME,
    concluido_por VARCHAR(255),
    ativo BOOLEAN NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);