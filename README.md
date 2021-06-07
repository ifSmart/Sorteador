# sorteador
Software sorteador da premiação da arrecadação beneficente da ifSmartJr. para o dia 10/07/21.

Para compilar, use a classe *TelaPrincipal.java* que possui o método *public static void main*.

A aplicação foi desenvolvida em JavaSE versão 1.8.

Para uma demonstração rápida, acesse a pasta *Demonstração Compilada* e executa o arquivo *Sorteador.jar*.

O software salva os dados dos contribuintes em arquivos e faz o sorteio do vencedor.

Funcionamento do software:

A aplicação é regida pelas regras do edital da arrecadação beneficente presente no formulário de cadastro.

A aplicação permite duas formas de cadastrar o nome, valor e CPF do contribuinte: De forma individual e por CSV emitido pelo Google Forms (feito exclusivamente para o formulário desta arrecadação).

A tela principal contém 5 opções:

-> Realizar Cadastro

- Cadastra contribuinte por contribuinte com seu nome, valor contribuído e CPF.
- Ao final de cada cadastro bem sucedido, uma nova pasta chamada *Listas* é criada, e depois arquivos são criados dentro dela, *lista_processamento.txt* e *lista_amostragem.txt*.
- Na lista de processamento são salvos o nome e o CPF do contribuinte por x vezes o número de divisores inteiros por 5 do valor contribuído.

Exemplo: Fulano de tal contribuiu com R$ 10, logo a lista ficará da seguinte forma:

*FULANO DE TAL, 123.456.789-45*.
*FULANO DE TAL, 123.456.789-45*.

- Na lista de amostragem são mostrados os dados de uma forma mais apresentável, já que essa lista será usada para o público, com o CPF sendo mascarado.

Seguindo o mesmo exemplo, a lista ficará da seguinte forma:

*NOME: FULANO DE TAL, CPF: ###.456.789-##, VALOR CONTRIBUÍDO: R$ 10, PONTOS: 2*.

-> Realizar Cadastro (CSV)

- Essa opção importa de dentro do diretório *...\Listas* o arquivo *lista_processamento_csv.csv* e faz a mesma coisa que a opção Realizar Cadastro.

-> Iniciar Premiação

- Essa opção inicia a premiação, lendo as duas listas e apresentando a lista de processamento na tela. A leitura das listas é realizada pela classe *Arquivo.java* através do método *.getLista(<Nome da Lista>)* e retorna um *ArrayList <String>* com cada posição preenchida correspondente às posições da lista.
Em diante, o sorteio é realizado através da classe *Sorteio.java* através do método *.Sortear(<ArrayList> <String>);*. Toda a lógica matemática está presente nessa classe.
Ao final do sorteio, uma mensagem com as informações dos vencedores é informada na tela.
As mesmas informações são impressas em um arquivo chamado *vencedores.txt* dentro de uma pasta criada chamada *Vencedores* dentro do diretório da pasta raíz em que o programa está sendo executado.


-> Notas e Informações

- Mostra as informações do software.

-> Sair

- Finaliza o programa.

© 2021 - Desenvolvido pela ifSmartJr.

Diretoria de Projetos

Aloísio Nunes da Mota Filho, Diretor de Projetos.
Raphael Rodrigues de Sena, Suplente.
Ian da Silva Conceição, Estagiário.
Narciso Francisco de Oliveira.