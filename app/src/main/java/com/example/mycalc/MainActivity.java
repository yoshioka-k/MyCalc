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
        buttonList.add((Button) findViewById(R.id.btZero));
        buttonList.add((Button) findViewById(R.id.btOne));
        buttonList.add((Button) findViewById(R.id.btTwo));
        buttonList.add((Button) findViewById(R.id.btThree));
        buttonList.add((Button) findViewById(R.id.btFour));
        buttonList.add((Button) findViewById(R.id.btFive));
        buttonList.add((Button) findViewById(R.id.btSix));
        buttonList.add((Button) findViewById(R.id.btSeven));
        buttonList.add((Button) findViewById(R.id.btEight));
        buttonList.add((Button) findViewById(R.id.btNine));
        // 四則演算+αボタンを取得
        buttonList.add((Button) findViewById(R.id.btAllclear));
        buttonList.add((Button) findViewById(R.id.btClear));
        buttonList.add((Button) findViewById(R.id.btDivided));
        buttonList.add((Button) findViewById(R.id.btTimes));
        buttonList.add((Button) findViewById(R.id.btMinus));
        buttonList.add((Button) findViewById(R.id.btPlus));
        buttonList.add((Button) findViewById(R.id.btEquals));
        buttonList.add((Button) findViewById((R.id.btPoint)));

        ButtonClickListener buttonClicklistener = new ButtonClickListener();

        for (Button button : buttonList) {
            button.setOnClickListener(buttonClicklistener);
        }
    }

    private class ButtonClickListener implements View.OnClickListener {

        private List<BigDecimal> _numList = new ArrayList<>();
        private List<Character> _funcList = new ArrayList<>();
        private String _inputValue = "";
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

                // 記号ボタン(+、-、*、/、=、%)の場合
                case R.id.btPlus:
                    inputFunc = '+';
                    addTextFunc();
                    break;
                case R.id.btMinus:
                    inputFunc = '-';
                    addTextFunc();
                    break;
                case R.id.btTimes:
                    inputFunc = '×';
                    addTextFunc();
                    break;
                case R.id.btDivided:
                    inputFunc = '÷';
                    addTextFunc();
                    break;

                case R.id.btEquals:
                    if (_funcList.size() != 0 && _funcList.get(0) == '=') {
                        break;
                    }
                    inputFunc = '=';
                    addTextFunc();
                    break;

                case R.id.btClear:
                    total.setText("0");
                    _inputValue = "";
                    break;
                case R.id.btAllclear:
                    total.setText("0");
                    _numList.clear();
                    _inputValue = "";
                    formula.setText("");
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
            if (_funcList.size() != 0 && _funcList.get(0) == '=') {
                _numList.clear();
                _funcList.clear();
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
            引　数：なし
            戻り値：なし */
        private void addTextFunc() {
            if (_funcList.size() != 0 && _funcList.get(0) == '=') {
                _funcList.set(0, inputFunc);
            }
            if (!(_inputValue.equals(""))) {
                addList();
                _inputValue = "";
                if (_numList.size() > 1) {
                    String result = calculater().toPlainString();
                    total.setText(result);
                }
            }
            if (!(_funcList.contains('='))) {
                String formulaFunc = String.valueOf(inputFunc);
                formula.setText(formulaFunc);
            } else {
                formula.setText("");
            }
        }

        /*  機　能：数字配列_numListと_funcListにそれぞれ値をセットする
            引　数：なし
            戻り値：なし */
        private void addList() {
            _numList.add(new BigDecimal(_inputValue));
            _funcList.add(inputFunc);
        }

        /*  機　能：_funcListから記号を受け取り、その記号に応じた計算結果を返す
            引　数：なし
            戻り値：演算した結果を10進数に直したもの(BigDecimal) */
        private BigDecimal calculater() {
            BigDecimal result = new BigDecimal(0);
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
                    result = _numList.get(0).divide(_numList.get(1), 12, BigDecimal.ROUND_HALF_UP);
            }

            _numList.set(0, result);
            _numList.remove(1);
            _funcList.remove(0);
            return result.stripTrailingZeros();
        }
    }
}