# MottuMap
 
MottuMap é um projeto que utiliza visão computacional e tecnologia RFID para facilitar a localização e a gestão das motos nos pátios da Mottu.
 
Cada moto será equipada com uma etiqueta RFID, que ao ser lida por sensores instalados nas zonas do pátio, gera um novo registro no histórico de movimentação da moto. Isso permite rastrear em tempo real a zona em que a moto está localizada.
 
Além disso, para uma identificação ainda mais precisa, o sistema conta com câmeras de visão computacional que mapeiam as vagas dentro de cada zona, permitindo associar a moto a uma posição específica (vaga) dentro da zona.
 
Com isso, será possível acompanhar o histórico de movimentação de cada moto, sabendo por onde ela passou e onde está atualmente.

## **Sobre a API e a Infraestrutura**

 
A API do projeto foi desenvolvida com Spring Boot e agora está totalmente preparada para execução em nuvem (Azure) com:

- Deploy automatizado via GitHub Actions
- Execução do .jar no Azure App Service (Web App em Linux)
- Banco de dados SQL Server na Azure
- Versionamento do banco com Flyway (migrations automáticas)
- Autenticação e autorização com Spring Security
- Configurações externas via variáveis de ambiente (Secrets)
- Scripts `.sh` para provisionamento automatizado de toda a infraestrutura

A aplicação pode ser acessada via navegador (Thymeleaf) ou  por requisições HTTP.

Consulte a documentação completa da API em:
````
https://github.com/eduardogdias/java_sprint3
````

---

## Arquitetura da Aplicação

![](images/diagrama.png)

Siga as instruções abaixo para fazer o deploy da API na Azure utilizando GitHub Actions, Web App e SQL Server.

---

## 1. Faça o Fork do projeto

Acesse o repositório original e clique em **Fork**:

```https://github.com/eduardogdias/devops_sprint3.git```
 


## 2. Clone seu repositório no Azure CLI

```bash
git clone https://github.com/{seu-usuario}/{seu-repositorio}.git
cd {seu-repositorio}
```



## 3. Configure as variáveis do projeto

Abra o arquivo `variables.sh`:
```bash
nano variables.sh
```

Se quiser personalizar nomes, região ou credenciais, altere nesse arquivo.
**Obs:** Atualize também o valor de `GITHUB_REPO_NAME` com o seu repositório.



## 4. Dê permissão e execute o script de criação e deploy
```bash
chmod +x criacao-deploy.sh
./criacao-deploy.sh
```

Ao final, será solicitada autorização do GitHub para que o Azure crie o workflow de deploy.



## 5. Configure os Secrets no GitHub

Entre em: Settings → Secrets and variables → Actions → New repository secret

Crie os seguintes secrets:
```
SPRING_DATASOURCE_USERNAME=
SPRING_DATASOURCE_PASSWORD=
SPRING_DATASOURCE_URL=
```
Se não alterou nada no `variables.sh`, utilize:


- SPRING_DATASOURCE_USERNAME=
```
admsql
```
- SPRING_DATASOURCE_PASSWORD=
```
Fiap@2tdsvms
```
- SPRING_DATASOURCE_URL=
```
jdbc:sqlserver://sqlserver-rm5555.database.windows.net:1433;database=db-rm5555;user=admsql@sqlserver-rm5555;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;
```

Se alterou o banco ou servidor, copie a string correta no portal Azure em:
Banco de Dados → Cadeias de Conexão → JDBC



## 6. Ajuste o arquivo .yml do GitHub Actions

O arquivo estará em: `.github/workflows/main_webapp-rm5555.yml`


Localize o comando `run: mvn clean install` e adicione:
````yml
env:
  SPRING_DATASOURCE_URL: ${{ secrets.SPRING_DATASOURCE_URL }}
  SPRING_DATASOURCE_USERNAME: ${{ secrets.SPRING_DATASOURCE_USERNAME }}
  SPRING_DATASOURCE_PASSWORD: ${{ secrets.SPRING_DATASOURCE_PASSWORD }}
````
**Obs:** Atenção à indentação.
Faça o commit e espere o build e deploy serem feitos automaticamente.

![](images/conectadoGitHubTerminal.png)
![](images/conectadoGitHub.png)




## 7. Acesse a aplicação

Se você não alterou os nomes padrão do `variables.sh`, o link será:
````bash
https://webapp-rm5555.azurewebsites.net
````

Caso tenha alterado:
````bash
https://<nome-do-webapp>.azurewebsites.net
````



## 8. Acesso / Usuários

Foram definidos dois usuários, assim que a aplicação inicia, através de Migrations com **Flyway**:
- **ADMIN:** tem permissão pra fazer o CRUD completo e acessar todas as telas do sistema.
- **USER:** só pode acessar páginas de listagem e fazer requisições do tipo GET. 

Para acessar as telas, basta logar com um desses usuários:
| Email             | Senha     | Role (permissão)  |
| ----------------- | --------- | ----------------- |
| admin@mottu.com   | 123456    | ADMIN             |
| user@mottu.com    | 123456    | USER              |

![](images/login.png)


---

# Estrutura do Banco de Dados
![](images/banco.png)
 
**Entidades:** Patio, Zona, Moto, Sensor, Historico

---

# Exemplos de Requisições HTTP
(Somente um ADMIN pode fazer o CRUD completo)
 
- Exemplo de Request **Patio**:
````json
{
    "nome": "Mottu Space 3",
    "endereco": "Av. Butantan, 552"
}
````
 
 
- Exemplo de Request **Zona**:
````json
{
    "tipo": "Reparo",
    "qtdVaga": 10,
    "patioId": 1
}
````
 
- Exemplo de Request **Moto**:
````json
{
    "placa": "1234567",
    "chassi": "12345678901234567",
    "modelo": "Pop"
}
````
 
- Exemplo de Request **Sensor**:
````json
{
  "localizacao": "Entrada principal",
  "data": "2025-05-23",
  "hora": "22:30"
}
````
 
- Exemplo de Request **Historico**:
````json
{
  "posicao": 2,
  "motoId": 2,
  "zonaId": 1,
  "sensorId": 2
}
````

---

## Requisições de Teste
(Somente um ADMIN pode fazer o CRUD completo)
 
-  **GET**:
````
GET http://webapp-rm5555.azurewebsites.net//api/motos
````


-  **POST**:
````
POST https://webapp-rm5555.azurewebsites.net/api/motos
````
````json
{
    "placa": "1234577",
    "chassi": "12345678901234577",
    "modelo": "E"
}
````


-  **PUT**:
````
PUT https://webapp-rm5555.azurewebsites.net/api/motos/2
````
````json
{
    "placa": "7734577",
    "chassi": "77345678901234577",
    "modelo": "Sport"
}
````

-  **DELETE**:
````
DELETE https://webapp-rm5555.azurewebsites.net/api/motos/3
````