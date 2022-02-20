# MyCalc

JavaとAndroid Studioで制作した基本的な電卓アプリ.

MyCalcは四則演算の計算が出来る.<br>
小数点の計算にも対応済みで,小数点以下が0の場合は整数に直す.<br>

## Features

タブレット(Pixel C)で起動しても,レイアウトが崩れないように対応済み.<br>
計算途中にのみ,計算結果の上部に直前に押されたボタンの記号を表示する(例えば1 + 3をした場合は+).<br>
=が押された場合は記号を表示せずに,上部の表示をクリアにする機能がある.<br>

## Required Environment

* Android Studio 2020.3.1 Patch 4
* API 30

## Usage

1~9のボタンを押下すると,対応した数字が計算結果に表示される.<br>
四則演算のボタンを押下すると,直前に入力した数字と,その一つ前に入力した数字を計算する.<br>
ACボタンを押下すると,保持していた全ての数字と記号がクリアされ,計算結果に0が表示される.<br>
Cボタンを押下すると,現在計算結果に表示されている数字だけをクリアする.<br>
.ボタンを押下すると,計算結果に表示されている数字に小数点を追加する.<br>

## Note

スマホサイズ,タブレットサイズともに横画面には非対応.<br>

## Author

* 作成者"yoshioka-k"<br>

## License

Copyright (c) 2021 yoshioka-k<br>
Released under the MIT license
https://opensource.org/licenses/mit-license.php


