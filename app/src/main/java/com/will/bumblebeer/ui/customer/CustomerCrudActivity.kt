package com.will.bumblebeer.ui.customer

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.will.bumblebeer.R
import com.will.bumblebeer.databinding.ActivityCustomerCrudBinding
import com.will.bumblebeer.databinding.LoadingDialogBinding
import com.will.bumblebeer.ui.customer.CustomerConstants.CRUD_ACTION_CREATE
import com.will.bumblebeer.ui.customer.CustomerConstants.CRUD_ACTION_DELETE
import com.will.bumblebeer.ui.customer.CustomerConstants.CRUD_ACTION_READ
import com.will.bumblebeer.ui.customer.CustomerConstants.CRUD_ACTION_TYPE
import com.will.bumblebeer.ui.customer.CustomerConstants.CRUD_ACTION_UPDATE
import kotlinx.android.synthetic.main.activity_customer_crud.*

class CustomerCrudActivity : AppCompatActivity() {

    //TODO In progress
    /**
     * Dialog to show when the app is processing something
     */
    private var progressDialog: Dialog? = null

    /**
     * Activity View Binding
     */
    private val bindingActivity by lazy {
        ActivityCustomerCrudBinding.inflate(layoutInflater)
    }

    /**
     * Progress View Binding
     */
    private val bindingProgress by lazy {
        LoadingDialogBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindingActivity.root)

        //showLoadingState()
        initView()
        setupButtonCancel()
        setupButtonDelete()
        setupButtonSave()
    }

    private fun showLoadingState() {
        if (progressDialog == null) {
            progressDialog = buildLoadingDialog(this)
        }
        progressDialog?.show()
    }

    private fun hideLoadingState() {
        progressDialog?.dismiss()
    }

    private fun buildLoadingDialog(context: Context): Dialog {
        val dialog = Dialog(context)
        dialog.setContentView(bindingProgress.root)
        dialog.setCancelable(true)
        return dialog
    }

    private fun setupButtonCancel() = btn_cancel.setOnClickListener{
        //TODO - Implementar o retorno para tela Customers
        Toast.makeText(
            this,
            R.string.customer_cancel_not_implemented_yet,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun setupButtonDelete() = btn_delete.setOnClickListener {
        //TODO - Implementar a chamada de Delete Customer
        Toast.makeText(
            this,
            R.string.customer_delete_not_implemented_yet,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun setupButtonSave() = btn_save.setOnClickListener {
        //TODO - Implementar a chamada de Insert Customer
        Toast.makeText(
            this,
            R.string.customer_save_not_implemented_yet,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun initView(){
        val actionType = intent.getStringExtra(CRUD_ACTION_TYPE)
        when(actionType){
            CRUD_ACTION_CREATE -> btn_delete.isVisible = false
            CRUD_ACTION_READ -> btn_delete.isVisible = false
            CRUD_ACTION_UPDATE -> btn_delete.isVisible = false
            CRUD_ACTION_DELETE -> btn_delete.isVisible = true
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
