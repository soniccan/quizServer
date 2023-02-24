  

背景
--------------
サーバーを書くという経験をしようと思い、指導教員による指導のもとに書いたプログラムである。
マルチクライアントに対応したクイズを出題するサーバーを作りました。

実装
----------------

サーバーがマルチクライアントに対応するためには、クライアントの接続要求に対して、Threadを発行し、Threadとclientが１対１対応である必要があります。
よって、clientの接続に対して、Threadを扱うインスタンスを立ち上げる実装を行いました。
```
Socket socket = serverSocket.accept();

new clientThread(socket,server_data).start();
```

しかし、clientが一つの変数に対し、同時に書き込みを行うとデータの安全性が損なわれ、意図しないデータが書き込まれること
があります。

これを回避するために共通で使う変数を書き換える関数には、`syncronized` キーワードを用いて、複数のスレッドがアクセスしても一つずつ処理を行うように変更します。

`public  synchronized  short  player_inc()`
`public  synchronized  boolean  fin_q(int  max)`


server コンパイル
----------------

javac quizserver/*.java

java quizserver/MulticlientServer


client
----------------------
javac client/*.java

java client/Client [hostname]



 // プロトコルをIPAddress + "#" + "Message"とする。
