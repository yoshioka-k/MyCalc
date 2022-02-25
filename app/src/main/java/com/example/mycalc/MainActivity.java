package com.example.mycalc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Button> buttonList = new ArrayList<>();
        // 数字ボタンを取得　0～9
        buttonList.add(findViewById(R.id.btZero));
        buttonList.add(findViewById(R.id.btOne));
        buttonList.add(findViewById(R.id.btTwo));
        buttonList.add(findViewById(R.id.btThree));
        buttonList.add(findViewById(R.id.btFour));
        buttonList.add(findViewById(R.id.btFive));
        buttonList.add(findViewById(R.id.btSix));
        buttonList.add(findViewById(R.id.btSeven));
        buttonList.add(findViewById(R.id.btEight));
        buttonList.add(findViewById(R.id.btNine));
        // 四則演算+αボタンを取得
        buttonList.add(findViewById(R.id.btAllclear));
        buttonList.add(findViewById(R.id.btClear));
        buttonList.add(findViewById(R.id.btDivided));
        buttonList.add(findViewById(R.id.btTimes));
        buttonList.add(findViewById(R.id.btMinus));
        buttonList.add(findViewById(R.id.btPlus));
        buttonList.add(findViewById(R.id.btEquals));
        buttonList.add(findViewById(R.id.btPoint));

        ButtonClickListener buttonClicklistener = new ButtonClickListener();

        for (Button button : buttonList) {
            button.setOnClickListener(buttonClicklistener);
        }
    }

    private class ButtonClickListener implements View.OnClickListener {

        private List<BigDecimal> _numList = new ArrayList<>();
        private List<Character> _funcList = new ArrayList<>();
        private String _inputValue = "0";
        char inputFunc;
        TextView formula = findViewById(R.id.tvFormula);
        TextView total = findViewById(R.id.tvTotal);

        @Override
        public void onClick(View view) {
            int btId = view.getId();
            // クリックされたボタンのIDを取得して、押されたボタンによって処理を実行
            switch (btId) {
                // 数字ボタン(0～9)の場合
                case R.id.btZero:
                    inputFunc = '0';
                    if (!(total.getText().equals("0"))) {
                        addText();
                    }
                    break;
                case R.id.btOne:
                    inputFunc = '1';
                    addText();
                    break;
                case R.id.btTwo:
                    inputFunc = '2';
                    addText();
                    break;
                case R.id.btThree:
                    inputFunc = '3';
                    addText();
                    break;
                case R.id.btFour:
                    inputFunc = '4';
                    addText();
                    break;
                case R.id.btFive:
                    inputFunc = '5';
                    addText();
                    break;
                case R.id.btSix:
                    inputFunc = '6';
                    addText();
                    break;
                case R.id.btSeven:
                    inputFunc = '7';
                    addText();
                    break;
                case R.id.btEight:
                    inputFunc = '8';
                    addText();
                    break;
                case R.id.btNine:
                    inputFunc = '9';
                    addText();
                    break;

                // 記号ボタン(+、-、*、/、=)の場合
                case R.id.btPlus:
                    funcRemove('+');
                    addTextFunc('+');
                    inputFunc = '+';
                    break;
                case R.id.btMinus:
                    funcRemove('-');
                    addTextFunc('-');
                    inputFunc = '-';
                    break;
                case R.id.btTimes:
                    funcRemove('×');
                    addTextFunc('×');
                    inputFunc = '×';
                    break;
                case R.id.btDivided:
                    funcRemove('÷');
                    addTextFunc('÷');
                    inputFunc = '÷';
                    break;

                case R.id.btEquals:
                    if (_funcList.size() != 0 && _funcList.get(0) == '=') {
                        break;
                    }
                    addTextFunc('=');
                    inputFunc = '=';
                    break;

                case R.id.btClear:
                    total.setText("0");
                    _inputValue = "0";
                    break;
                case R.id.btAllclear:
                    total.setText("0");
                    clearMethod();
                    break;

                case R.id.btPoint:
                    if (_funcList.size() != 0 && _funcList.get(0) == '=') {
                        total.setText("0");
                        _numList.clear();
                        _funcList.clear();
                    }
                    inputFunc = '.';
                    String subContains = (String) total.getText();
                    if (!(subContains.contains("."))) {
                        addTextView();
                        _inputValue += inputFunc;
                    }
                    break;
            }
        }

        /*  機　能：数字を入力できる状態か判断して、値を連結させる
            引　数：なし
            戻り値：なし */
        private void addText() {
            /*  _funcList 0番目に"="が入っている時にクリアにする
                例えば「1 + 3 =」と連続で入力した場合、calculater()の演算が終わった時点で
                _numListと_funcListにはresultと"="が入っているため
                かつ、sizeが0ではないことを保証した上でget(0)しないとエラーになるため
             */
            if (_funcList.size() != 0 && _funcList.get(0) == '=') {
                _numList.clear();
                _funcList.clear();
            }
            if (_inputValue.equals("0")) {
                _inputValue = "";
            }
            if (_inputValue.length() == 0) {
                total.setText("");
            }
            addTextView();
            _inputValue += inputFunc;
        }

        /*  機　能：totalに表示されている数値と入力された数値を連結して返す
            引　数：なし
            戻り値：なし */
        private void addTextView() {
            total.setText(total.getText().toString() + inputFunc);
        }

        /*  機　能：記号ボタンが押された際に、その直前の計算を行って値を返す。=だけ挙動が違う
            引　数：後から入力した記号func(char)
            戻り値：なし */
        private void addTextFunc(char func) {
            String res = "";
            if (_funcList.size() != 0 && _funcList.get(0) == '=') {
                _funcList.set(0, func);
                return;
            }
            if (!(inputFunc == '+' || inputFunc == '-' || inputFunc == '×' || inputFunc == '÷')) {
                addList(func);
                _inputValue = "0";
                if (_numList.size() > 1) {
                    res = calculater();
                    total.setText(res);
                }
            }
            if (!(_funcList.contains('='))) {
                String formulaFunc = String.valueOf(func);
                formula.setText(formulaFunc);
            } else {
                formula.setText("");
            }
        }

        /*  機　能：数字配列_numListと_funcListにそれぞれ値をセットする
            引　数：後から入力した記号func(char)
            戻り値：なし */
        private void addList(char func) {
            _numList.add(new BigDecimal(_inputValue));
            _funcList.add(func);
        }

        /*  機　能：直前に入力されたinputFuncが記号かどうか判断し、記号を上書きする
            引　数：上書きしたい記号func(char)
            戻り値：なし
         */
        private void funcRemove(char func) {
            if (inputFunc == '+' || inputFunc == '-' || inputFunc == '×' || inputFunc == '÷' || inputFunc == '=') {
                inputFunc = func;
                _funcList.set(_funcList.size() -1, inputFunc);
            }
        }

        /*  機　能：numListとfuncListと_inputValueとformuraをクリアにする
            引　数：なし
            戻り値：なし
         */
        private void clearMethod() {
            _numList.clear();
            _funcList.clear();
            _inputValue = "0";
            formula.setText("");
        }

        /*  機　能：_funcListから記号を受け取り、その記号に応じた計算結果を返す
            引　数：なし
            戻り値：演算した結果を10進数に直したもの(BigDecimal) */
        private String calculater() {
            BigDecimal result = new BigDecimal(0);
            String msg = "";
            switch(_funcList.get(0)) {
                case '+':
                    result = _numList.get(0).add(_numList.get(1));
                    break;
                case '-':
                    result = _numList.get(0).subtract(_numList.get(1));
                    break;
                case '×':
                    result = _numList.get(0).multiply(_numList.get(1));
                    break;
                case '÷':
                    if (_numList.get(0).signum() == 0 && _numList.get(1).signum() == 0) {
                        msg = "結果は未定義です";
                        clearMethod();
                    } else if (_numList.get(1).signum() == 0) {
                        msg = "0 で割ることはできません";
                        clearMethod();
                    } else {
                        result = _numList.get(0).divide(_numList.get(1), 12, BigDecimal.ROUND_HALF_UP);
                    }
            }
            if (!(msg.equals("結果は未定義です") || msg.equals("0 で割ることはできません"))) {
                _numList.set(0, result);
                _numList.remove(1);
                _funcList.remove(0);
                result = result.stripTrailingZeros(); // 末尾の0を削除する
                msg = result.toPlainString();
            }
            return msg;
        }
    }
}