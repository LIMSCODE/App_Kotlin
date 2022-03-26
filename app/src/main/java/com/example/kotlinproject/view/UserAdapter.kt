package com.example.kotlinproject.view

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.R
import com.example.kotlinproject.model.UserData

class UserAdapter(val c : Context, val userList:ArrayList<UserData>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    //공통 사용
    inner class UserViewHolder (val v : View) : RecyclerView.ViewHolder(v) {
        var name : TextView
        var mbNum : TextView
        var mMenus : ImageView

        init {
            name = v.findViewById<TextView>(R.id.mTitle)
            mbNum = v.findViewById<TextView>(R.id.mSubTitle)
            mMenus = v.findViewById(R.id.mMenus)
            mMenus.setOnClickListener { popupMenus(it) }
        }

        private fun popupMenus(v:View) {

            val position = userList[adapterPosition]
            val popupMenus = PopupMenu(c,v)     //팝업메뉴
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener {

                when (it.itemId) {

                    R.id.editText -> {  //더보기버튼 (수정)

                        val v = LayoutInflater.from(c).inflate(R.layout.write_post, null)
                        val name = v.findViewById<EditText>(R.id.userName)
                        val number = v.findViewById<EditText>(R.id.userNo)

                                AlertDialog.Builder(c)
                                    .setView(v)
                                    .setPositiveButton("Ok") {
                                        dialog,_ ->
                                        position.userName = name.text.toString()
                                        position.userMb = number.text.toString()
                                        notifyDataSetChanged()
                                        Toast.makeText(c, "User info is Edited ", Toast.LENGTH_SHORT).show()
                                        dialog.dismiss()
                                    }

                                    .setNegativeButton("Cancel") {
                                        dialog,_ ->
                                        dialog.dismiss()

                                    }
                                    .create()
                                    .show()

                        Toast.makeText(c, "EditText Button is Clicked", Toast.LENGTH_SHORT).show()
                        true
                    }

                    R.id.deleteText -> {    //더보기버튼 (삭제)

                        AlertDialog.Builder(c)
                            .setTitle("DETELTE")
                            .setIcon(R.drawable.ic_baseline_warning_24)
                            .setMessage("정말 삭제하겠습니까?")
                            .setPositiveButton("Yes") {
                                dialog,_ ->
                                userList.removeAt(adapterPosition)
                                notifyDataSetChanged()
                                Toast.makeText(c, "Deleted", Toast.LENGTH_SHORT).show()
                                dialog.dismiss()
                            }
                            .create()
                            .show()

                        Toast.makeText(c, "Delete Button is Clicked", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> true
                }

            }
            popupMenus.show()

            val popup = PopupMenu :: class.java.getDeclaredField("mPopup")
            popup.isAccessible = true

            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(menu,true)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.list_item, parent, false)
        return UserViewHolder(v)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val newList = userList[position]
        holder.name.text =  newList.userName
        holder.mbNum.text = newList.userMb
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}