Ideas:
- Criar variável para guardar timstamp, comparando o timestamp to ultimo packet enviado com o timestamp do novo packet depois de ocorrer erro de envio do mesmo;

O que deve ser feito?
- Desenvolver um sistema distribuído que funciona como um fórum de discussão semelhante ao Reddit. Esse sistema terá três serviços principais, que devem se comunicar entre si:

Serviço de Utilizadores (Users): Gerencia os usuários do sistema, permitindo criar, editar, remover e buscar usuários.

Serviço de Conteúdos (Content): Controla os posts e respostas do fórum, além dos votos (up-vote e down-vote).

Serviço de Imagens (Images): Armazena e recupera imagens associadas a avatares e posts.

- Os servidores precisam se comunicar usando REST (JAX-RS) e, opcionalmente, gRPC.

Principais Funcionalidades
- Criar, editar e apagar usuários e posts.
- Adicionar respostas a posts e respostas a respostas.
- Permitir votos (positivos e negativos) nos conteúdos.
- Gerenciar imagens de perfil e imagens nos posts.
- Implementar auto-configuração para descoberta automática de servidores via multicast.

Como testar? O projeto será validado por meio de uma bateria de testes automática (ainda a ser disponibilizada). No entanto, você pode testar manualmente os serviços desenvolvidos verificando:
- Se os usuários podem ser criados, editados, removidos e buscados corretamente.
- Se os posts e respostas são manipulados corretamente.
- Se os votos nos conteúdos são registrados corretamente, respeitando as restrições.
- Se as imagens podem ser armazenadas e recuperadas corretamente.
- Se os servidores conseguem se descobrir automaticamente e comunicar entre si.
- Se a API segue o padrão REST e (opcionalmente) gRPC.

Critérios de Avaliação
- Funcionalidade completa do sistema e conformidade com a especificação.
- Suporte a falhas temporárias de comunicação.
- Código bem estruturado e seguindo boas práticas.
- Implementação correta da auto-configuração.