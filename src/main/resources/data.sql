-- TABELA DE EQUIPES
CREATE TABLE teams (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

INSERT INTO teams (name) VALUES
('Diretoria Executiva'),
('Administrativo'),
('Financeiro'),
('Recursos Humanos'),
('Marketing'),
('Vendas'),
('Atendimento ao Cliente'),
('Tecnologia da Informação'),
('Desenvolvimento de Produtos'),
('Operações'),
('Logística'),
('Jurídico'),
('Compras'),
('Qualidade'),
('Produção'),
('Pesquisa e Desenvolvimento'),
('Infraestrutura'),
('Comunicação'),
('Treinamento e Desenvolvimento'),
('Segurança do Trabalho');

-- TABELA DE USUÁRIOS
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    team_id BIGINT NOT NULL,
    profile ENUM('ADMINISTRADOR', 'COLABORADOR') DEFAULT 'COLABORADOR',
    FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE CASCADE
);

-- TABELA DE TAREFAS
CREATE TABLE tasks (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    due_date DATE,
    status ENUM('PENDENTE', 'EM_ANDAMENTO', 'CONCLUIDA') DEFAULT 'PENDENTE',
    team_id BIGINT NOT NULL,
    responsible_id BIGINT NOT NULL,
    FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE CASCADE,
    FOREIGN KEY (responsible_id) REFERENCES users(id) ON DELETE CASCADE
);