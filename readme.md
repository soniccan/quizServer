#quizServer

背景
--------------
サーバーを書くという経験を得る作成したプログラム
マルチクライアントに対応するためにThreadを用いた。


server コンパイル
----------------

javac quizserver/*.java

java quizserver/MulticlientServer


client
----------------------
javac client/*.java

java client/Client [hostname]



 // プロトコルをIPAddress + "#" + "Message"とする。

