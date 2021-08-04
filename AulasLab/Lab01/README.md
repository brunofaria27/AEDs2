#Tudo sobre a compilação dentro do WSL

- Instalando JRE/JDK padrão:
```bash
$ sudo apt update
$ sudo apt install default-jre
$ java -version                 // Confira se o java foi instalado com sucesso
$ sudo apt install default-jdk
$ javac -version                // Confira se o JDK foi instalado com sucesso
```
- Compilando os programas java:
```bash
$ cd (diretorio onde esta o programa)
$ javac programa.java                   // Compilar o programa java
$ java programa                         // Rodar o programa java
$ java programa < pub.in                // Ler o arquivo e colocar o que tem dentro para a entrada ao rodar o programa
$ java programa < pub.in > saida        // Colocar o que tem dentro de pub.in na entrada ao rodar o programa, colocar a saida no programa no arquivo "saida"
$ diff pub.out saida                    // Comparando o pub.out (saida desejavel) com o arquivo saida -> caso não retorne nada {Está correto} 
                                                                                                      -> caso retorne algo {Está errado}
                                        
                                        Exemplo de erro: 3c3
                                                         < Mutum
                                                         ---
                                                        > Gaivota
                                        Isso significa que devemos modificar a linha 3 no primeiro arquivo pela linha 3 no segundo para que se tornem idênticos. Ou seja, alterar Mutum para Gaivota.
```