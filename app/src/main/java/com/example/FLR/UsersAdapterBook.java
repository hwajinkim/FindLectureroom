package com.example.FLR;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.FLR.R;

import java.util.ArrayList;

public class UsersAdapterBook extends RecyclerView.Adapter<UsersAdapterBook.CustomViewHolder>{
    private ArrayList<PersonalData> mList = null; //새로운 학생정보를 저장하는 mList를 선언
    private Activity context = null;

    //MainActivity에서 객체 mAdapter를 만들때 생성자인 이부분으로 이동
    //      new UsersAdapter(this,              mArrayList);
    public UsersAdapterBook(Activity context, ArrayList<PersonalData> list) {
        this.context = context;//매개변수로 받아온 값(this)를 mAdapter 객체의 context 필드에 저장한다.
        this.mList = list;//매개변수로 받아온 값(list)를 mAdapter 객체의 mList 필드에 저장한다.
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView studentName;
        protected TextView peopleNumber;
        protected TextView roomNumber;
        protected TextView rentalStime;
        protected TextView rentalEtime;
        protected TextView rentalDate;
        protected TextView perposeOfUse;
        protected TextView bookStatus;
        protected TextView cancelReason;


        public CustomViewHolder(View view) {
            super(view);
            this.studentName = (TextView) view.findViewById(R.id.textView_list_student_name);    //view(item_list)객체 내의 textView_list_Etime 아이템을 view 객체의 필드 Etime에 초기화(inflate)
            this.peopleNumber = (TextView) view.findViewById(R.id.textView_list_people_num);//view(item_list)객체 내의 textView_list_Sub 아이템을 view 객체의 필드 Sub에 초기화(inflate)
            this.roomNumber = (TextView) view.findViewById(R.id.textView_list_room_num);    //view(item_list)객체 내의 textView_list_Stime 아이템을 view 객체의 필드 Stime에 초기화(inflate)
            this.rentalStime = (TextView) view.findViewById(R.id.textView_list_rental_stime);    //view(item_list)객체 내의 textView_list_Etime 아이템을 view 객체의 필드 Etime에 초기화(inflate)
            this.rentalEtime = (TextView) view.findViewById(R.id.textView_list_rental_etime);    //view(item_list)객체 내의 textView_list_Etime 아이템을 view 객체의 필드 Etime에 초기화(inflate)
            this.rentalDate = (TextView) view.findViewById(R.id.textView_list_rental_date);    //view(item_list)객체 내의 textView_list_Etime 아이템을 view 객체의 필드 Etime에 초기화(inflate)
            this.perposeOfUse = (TextView) view.findViewById(R.id.textView_list_perpose_of_use);    //view(item_list)객체 내의 textView_list_Etime 아이템을 view 객체의 필드 Etime에 초기화(inflate)
            this.bookStatus = (TextView) view.findViewById(R.id.textView_list_book_status);    //view(item_list)객체 내의 textView_list_Etime 아이템을 view 객체의 필드 Etime에 초기화(inflate)
            this.cancelReason = (TextView) view.findViewById(R.id.textView_list_cancel_reason);    //view(item_list)객체 내의 textView_list_Etime 아이템을 view 객체의 필드 Etime에 초기화(inflate)

        }
    }

    //<DB에서 검색한 내용을 정해진 레이아웃 형태로 화면에 출력해주기 위해 만든 xml을 inflate하여 viewHolder 객체로 만든 부분>
    //<item_list.xml을 객체화 시킨 부분 >
    //onCreateViewHolder() : 뷰홀더가 새로 만들어질 때 호출, 각 아이템을 위해 정의한 xml레이아웃을 이용해 뷰 객체를 만든다.
    //*inflate 정의:xml에 표기된 레이아웃들을 메모리에 객체화시키는 행동이다. -> setContentView(R.layout.activity_main);이런식으로
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_book, null);//item_list.xml 레이아웃을 inflate해서 view 변수에 저장
        CustomViewHolder viewHolder = new CustomViewHolder(view); //viewHolder 객체를 생성할 때 view를 매개변수로 넘겨주어 viewHolder 객체의 필드를 초기화함 (생성자 호출)

        return viewHolder;
    }

    //onBindViewHolder() : 뷰홀더가 재사용될 때 호출되므로 뷰객체는 기존 것을 그대로 사용하고 데이터만 바꾼다. 뷰홀더 객체를 파라미터 값으로 받기 때문에 뷰홀더에 현재 아이템에 맞는 걸로 설정하면 된다.
    //전달된 position 파라미터를 이용해서 ArrayList에서 객체를 꺼내어 설정할 수 있다.
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.studentName.setText(mList.get(position).getStudent_Name());
        viewholder.studentName.setTextColor(Color.parseColor("#000000"));

        viewholder.peopleNumber.setText(mList.get(position).getReservationPeopleNum());
        viewholder.peopleNumber.setTextColor(Color.parseColor("#000000"));

        viewholder.roomNumber.setText(mList.get(position).getReservationRoomNum());
        viewholder.roomNumber.setTextColor(Color.parseColor("#000000"));

        viewholder.rentalStime.setText(mList.get(position).getReservationRentalStartTime());
        viewholder.rentalStime.setTextColor(Color.parseColor("#000000"));

        viewholder.rentalEtime.setText(mList.get(position).getReservationRentalEndTime());
        viewholder.rentalEtime.setTextColor(Color.parseColor("#000000"));

        viewholder.rentalDate.setText(mList.get(position).getReservationRentalDate());
        viewholder.rentalDate.setTextColor(Color.parseColor("#000000"));

        viewholder.perposeOfUse.setText(mList.get(position).getReservationPerposeOfUse());
        viewholder.perposeOfUse.setTextColor(Color.parseColor("#000000"));

        viewholder.cancelReason.setText(mList.get(position).getReservationCancelReason());
        viewholder.cancelReason.setTextColor(Color.parseColor("#000000"));

        if((mList.get(position).getReservationBookStatus()).equals("0"))
        {
            viewholder.bookStatus.setText("예약 대기 중");

        }else if((mList.get(position).getReservationBookStatus()).equals("1")) {
            viewholder.bookStatus.setText("예약 승인");

        }else if((mList.get(position).getReservationBookStatus()).equals("-1")) {
            viewholder.bookStatus.setText("예약 취소");
        }
        viewholder.bookStatus.setTextColor(Color.parseColor("#000000"));
    }

    //getItemCount(): 어댑터에서 관리하는 아이템의 개수 반환, 어댑터가 ArrayList에 들어 있는 객체의 수를 알아야 하므로 ArrayList 내의 size()메서드를 호출하여 아이템의 개수를 얻는다.
    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}
