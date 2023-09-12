package com.example.FLR;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.FLR.R;

import java.util.ArrayList;

//Adapter란 리스트 모양의 뷰에 보이는 각각의 아이템을 관리하는 곳이다.
public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.CustomViewHolder> {//Adapter 클래스는 RecyclerView.Adapter를 상속하도록 함.
//해당 클래스를 상속하게 되면 3가지 구현해야 하는 메서드가 생긴다.

    private ArrayList<PersonalData> mList = null; //새로운 학생정보를 저장하는 mList를 선언
    private Activity context = null;

    //MainActivity에서 객체 mAdapter를 만들때 생성자인 이부분으로 이동
    //      new UsersAdapter(this,              mArrayList);
    public UsersAdapter(Activity context, ArrayList<PersonalData> list) {
        this.context = context;//매개변수로 받아온 값(this)를 mAdapter 객체의 context 필드에 저장한다.
        this.mList = list;//매개변수로 받아온 값(list)를 mAdapter 객체의 mList 필드에 저장한다.
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView Scode;
        protected TextView Sname;
        protected TextView Ssort;
        protected TextView Scre;
        protected TextView Sday;
        protected TextView Stime;
        protected TextView Etime;
        protected TextView Rnum;
        protected TextView Pnum;
        protected TextView hakjum;

        public CustomViewHolder(View view) {
            super(view);
            this.Scode = (TextView) view.findViewById(R.id.textView_list_Scode);    //view(item_list)객체 내의 textView_list_Etime 아이템을 view 객체의 필드 Etime에 초기화(inflate)
            this.Sname = (TextView) view.findViewById(R.id.textView_list_Sname);//view(item_list)객체 내의 textView_list_Sub 아이템을 view 객체의 필드 Sub에 초기화(inflate)
            this.Ssort = (TextView) view.findViewById(R.id.textView_list_Ssort);    //view(item_list)객체 내의 textView_list_Stime 아이템을 view 객체의 필드 Stime에 초기화(inflate)
            this.Scre = (TextView) view.findViewById(R.id.textView_list_Scre);    //view(item_list)객체 내의 textView_list_Etime 아이템을 view 객체의 필드 Etime에 초기화(inflate)
            this.Sday = (TextView) view.findViewById(R.id.textView_list_Sday);    //view(item_list)객체 내의 textView_list_Etime 아이템을 view 객체의 필드 Etime에 초기화(inflate)
            this.Stime = (TextView) view.findViewById(R.id.textView_list_Stime);    //view(item_list)객체 내의 textView_list_Etime 아이템을 view 객체의 필드 Etime에 초기화(inflate)
            this.Etime = (TextView) view.findViewById(R.id.textView_list_Etime);    //view(item_list)객체 내의 textView_list_Etime 아이템을 view 객체의 필드 Etime에 초기화(inflate)
            this.Rnum = (TextView) view.findViewById(R.id.textView_list_Rnum);    //view(item_list)객체 내의 textView_list_Etime 아이템을 view 객체의 필드 Etime에 초기화(inflate)
            this.Pnum = (TextView) view.findViewById(R.id.textView_list_Pnum);    //view(item_list)객체 내의 textView_list_Etime 아이템을 view 객체의 필드 Etime에 초기화(inflate)
            this.hakjum = (TextView)view.findViewById(R.id.textView_list_hakjum);
        }
    }

    //<DB에서 검색한 내용을 정해진 레이아웃 형태로 화면에 출력해주기 위해 만든 xml을 inflate하여 viewHolder 객체로 만든 부분>
    //<item_list.xml을 객체화 시킨 부분 >
    //onCreateViewHolder() : 뷰홀더가 새로 만들어질 때 호출, 각 아이템을 위해 정의한 xml레이아웃을 이용해 뷰 객체를 만든다.
    //*inflate 정의:xml에 표기된 레이아웃들을 메모리에 객체화시키는 행동이다. -> setContentView(R.layout.activity_main);이런식으로
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, null);//item_list.xml 레이아웃을 inflate해서 view 변수에 저장
        CustomViewHolder viewHolder = new CustomViewHolder(view); //viewHolder 객체를 생성할 때 view를 매개변수로 넘겨주어 viewHolder 객체의 필드를 초기화함 (생성자 호출)

        return viewHolder;
    }

    //onBindViewHolder() : 뷰홀더가 재사용될 때 호출되므로 뷰객체는 기존 것을 그대로 사용하고 데이터만 바꾼다. 뷰홀더 객체를 파라미터 값으로 받기 때문에 뷰홀더에 현재 아이템에 맞는 걸로 설정하면 된다.
    //전달된 position 파라미터를 이용해서 ArrayList에서 객체를 꺼내어 설정할 수 있다.
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

            viewholder.Scode.setText(mList.get(position).getStudent_Scode());
            viewholder.Scode.setTextColor(Color.parseColor("#000000"));

            viewholder.Sname.setText(mList.get(position).getStudent_Sname());
            viewholder.Sname.setTextColor(Color.parseColor("#000000"));

            viewholder.Ssort.setText(mList.get(position).getStudent_Ssort());
            viewholder.Ssort.setTextColor(Color.parseColor("#000000"));

            viewholder.Scre.setText(mList.get(position).getStudent_Scre());
            viewholder.Scre.setTextColor(Color.parseColor("#000000"));

            viewholder.Sday.setText(mList.get(position).getStudent_Sday());
            viewholder.Sday.setTextColor(Color.parseColor("#000000"));

            viewholder.Stime.setText(mList.get(position).getStudent_Stime());
            viewholder.Stime.setTextColor(Color.parseColor("#000000"));

            viewholder.Etime.setText(mList.get(position).getStudent_Etime());
            viewholder.Etime.setTextColor(Color.parseColor("#000000"));

            viewholder.Rnum.setText(mList.get(position).getStudent_Rnum());
            viewholder.Rnum.setTextColor(Color.parseColor("#000000"));

            viewholder.Pnum.setText(mList.get(position).getStudent_Pnum());
            viewholder.Pnum.setTextColor(Color.parseColor("#000000"));

            viewholder.hakjum.setTextColor(Color.parseColor("#000000"));
        }

    //getItemCount(): 어댑터에서 관리하는 아이템의 개수 반환, 어댑터가 ArrayList에 들어 있는 객체의 수를 알아야 하므로 ArrayList 내의 size()메서드를 호출하여 아이템의 개수를 얻는다.
    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}

