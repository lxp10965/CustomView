package com.xpl.quickindex;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends Activity {

    private ListView lvMain;
    private TextView tvWord;
    private IndexView ivWords;

    Handler handler = new Handler();
    private MyAdapter adapter;
    private ArrayList<Person> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMain = (ListView) findViewById(R.id.lv_main);
        tvWord = (TextView) findViewById(R.id.tv_word);
        ivWords = (IndexView) findViewById(R.id.iv_words);

        ivWords.setOnIndexChangeListener(new MyOnIndexChangeListener());

        initData();
        adapter = new MyAdapter();
        lvMain.setAdapter(adapter);//设置适配器

    }

    private void initData() {
        persons = new ArrayList<Person>();
        persons.add(new Person("张晓飞"));
        persons.add(new Person("杨光福"));
        persons.add(new Person("胡继群"));
        persons.add(new Person("刘畅"));

        persons.add(new Person("钟泽兴"));
        persons.add(new Person("尹革新"));
        persons.add(new Person("安传鑫"));
        persons.add(new Person("张骞壬"));

        persons.add(new Person("温松"));
        persons.add(new Person("李凤秋"));
        persons.add(new Person("刘甫"));
        persons.add(new Person("娄全超"));
        persons.add(new Person("张猛"));

        persons.add(new Person("王英杰"));
        persons.add(new Person("李振南"));
        persons.add(new Person("孙仁政"));
        persons.add(new Person("唐春雷"));
        persons.add(new Person("牛鹏伟"));
        persons.add(new Person("姜宇航"));

        persons.add(new Person("刘挺"));
        persons.add(new Person("张洪瑞"));
        persons.add(new Person("张建忠"));
        persons.add(new Person("侯亚帅"));
        persons.add(new Person("刘帅"));

        persons.add(new Person("乔竞飞"));
        persons.add(new Person("徐雨健"));
        persons.add(new Person("吴亮"));
        persons.add(new Person("王兆霖"));

        persons.add(new Person("阿三"));
        persons.add(new Person("李博俊"));
        persons.add(new Person("波仔"));

        //排序
        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getPinyin().compareTo(o2.getPinyin()); //将数据进行从 A-Z 排序
            }
        });
    }

    class MyAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return persons.size();
        }


        @Override
        public Object getItem(int position) {
            return null;
        }


        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHander viewHander;
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.item_word, null);
                viewHander = new ViewHander();
                viewHander.tv_word = convertView.findViewById(R.id.tv_word);
                viewHander.tv_name = convertView.findViewById(R.id.tv_name);
                convertView.setTag(viewHander);
            } else {
                viewHander = (ViewHander) convertView.getTag(); //重用Item视图
            }
            String name = persons.get(position).getName();
            String word = persons.get(position).getPinyin().substring(0,1);
            viewHander.tv_name.setText(name);
            viewHander.tv_word.setText(word);

            if (position==0){
                viewHander.tv_word.setVisibility(View.VISIBLE);
            }else {
                //得到前一个位置对应的字母，如果当前的字母和上一个相同，隐藏；否则就显示
                String preWord = persons.get(position-1).getPinyin().substring(0,1);
                if (word.equals(preWord)){
                    viewHander.tv_word.setVisibility(View.GONE); //GONE隐藏，视图也不占宽高了
                }else {
                    viewHander.tv_word.setVisibility(View.VISIBLE);
                }
            }
            return convertView;
        }
    }

    /**
     * 优化listView
     */
    static class ViewHander {
        TextView tv_word;
        TextView tv_name;
    }

    class MyOnIndexChangeListener implements IndexView.OnIndexChangeListener {

        //监听索引变化
        @Override
        public void onIndexChange(String word) {
            updateWord(word);
            updateListView(word);
        }
    }

    /**
     * 根据索引定位到列表对应位置
     * @param word
     */
    private void updateListView(String word) {
        for (int i=0;i<persons.size();i++){
            String listWord = persons.get(i).getPinyin().substring(0,1);  //A~Z
            if (word.equals(listWord)){
                lvMain.setSelection(i);
                return;
            }
        }
    }

    /**
     * 更新显示悬浮字母
     * @param word
     */
    private void updateWord(String word) {
        tvWord.setVisibility(View.VISIBLE);
        tvWord.setText(word);
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvWord.setVisibility(View.INVISIBLE);//INVISIBLE 隐藏，但视图还在
            }
        }, 1000);
    }


}
