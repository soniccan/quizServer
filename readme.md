

背景
--------------
サーバーを書くという経験を得る作成したプログラム
マルチクライアントに対応したクイズを出題するサーバーを作った。

実装
----------------

サーバーがマルチクライアントに対応するためには、クライアントの接続要求に対して、Threadを発行し、そのThreadが書き込むデータをスレッドセーフにする必要がある。




server コンパイル
----------------

javac quizserver/*.java

java quizserver/MulticlientServer


client
----------------------
javac client/*.java

java client/Client [hostname]



 // プロトコルをIPAddress + "#" + "Message"とする。

