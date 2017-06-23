###-------------------------------------------- Configuraçao do Banco de Dados------------------------
                                                    
                     1° primeiramente vai ate o arquivo "persistence" linha "9" e coloque um nome para 
                     "value="jdbc:mysql://localhost/BdProjecao"" mude o nome que e "BdProjecao" ou deixe
                     assim banco de dados ou deixe o nome que esta.Veja tambem ser seu banco tem senha ou 
                     username ser  nao deixa assim.                          
                     2°Abra o MYSQL.                                                                
                     3°Crie um conexao.                                                                                                                
                     5°Crie um banco de dados igual voce colocou no persistence.
                     6°As tabelas sao automaticas.
                     
###-------------------------------------------- Configuraçao Primeiro Usuario------------------------
                                                    
                     1° primeiramente vai ate o arquivo  src\main\webapp\WEB-INF\web.xml.                   
                     2°vai ate a linha <url-pattern>/private/ks*</url-pattern> e modifique deixando assim.                                                            
                     3°execute o sevidor.                                                         
                     5°vai ate o /projetoAgendamento/private/setor/listar.xhtml pelo navegador.
                     6°Cadastre um Setor com o Nome "Administrativo" sem aspa.
                     7°vai ate o /projetoAgendamento/private/employee/cadastroPessoa.xhtml
                     8°cadastre o primeiro usuario com o setor "Administrativo" sem aspa.
                     9°Salve
                     10°Vai ate a linha <url-pattern>/private/*</url-pattern> e modifique deixando assim voltando ao normal.
                     11°salve tudo e esta pronto.
