<template>
  <a-modal
    :title="title"
    :width="width"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item label="代理商名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'agentName', validatorRules.agentName]" placeholder="请输入代理商名称"></a-input>
        </a-form-item>
        <a-form-item label="代理商英文名" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'agentNameEn', validatorRules.agentNameEn]" placeholder="请输入代理商英文名"></a-input>
        </a-form-item>
        <a-form-item label="代理商缩写" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'agentNameAbbr', validatorRules.agentNameAbbr]" placeholder="请输入代理商缩写"></a-input>
        </a-form-item>
        <a-form-item label="等级（1省级代理；2市级代理；3区县代理）" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'level', validatorRules.level]" placeholder="请输入等级（1省级代理；2市级代理；3区县代理）" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="排序" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'agentOrder', validatorRules.agentOrder]" placeholder="请输入排序"></a-input>
        </a-form-item>
        <a-form-item label="描述" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'description', validatorRules.description]" placeholder="请输入描述"></a-input>
        </a-form-item>
        <a-form-item label="手机号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'mobile', validatorRules.mobile]" placeholder="请输入手机号"></a-input>
        </a-form-item>
        <a-form-item label="邮箱" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'email', validatorRules.email]" placeholder="请输入邮箱"></a-input>
        </a-form-item>
        <a-form-item label="地址" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'address', validatorRules.address]" placeholder="请输入地址"></a-input>
        </a-form-item>
        <a-form-item label="状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'status', validatorRules.status]" placeholder="请输入状态"></a-input>
        </a-form-item>
        <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'remark', validatorRules.remark]" placeholder="请输入备注"></a-input>
        </a-form-item>
        <a-form-item label="删除状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'timeDeleted', validatorRules.timeDeleted]" placeholder="请输入删除状态" style="width: 100%"/>
        </a-form-item>

      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: "SysAgentModal",
    components: { 
    },
    data () {
      return {
        form: this.$form.createForm(this),
        title:"操作",
        width:800,
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
          agentName: {rules: [
            {required: true, message: '请输入代理商名称!'},
          ]},
          agentNameEn: {rules: [
          ]},
          agentNameAbbr: {rules: [
          ]},
          level: {rules: [
          ]},
          agentOrder: {rules: [
          ]},
          description: {rules: [
          ]},
          mobile: {rules: [
            {required: true, message: '请输入手机号!'},
          ]},
          email: {rules: [
            {required: true, message: '请输入邮箱!'},
          ]},
          address: {rules: [
            {required: true, message: '请输入地址!'},
          ]},
          status: {rules: [
          ]},
          remark: {rules: [
          ]},
          timeDeleted: {rules: [
            {required: true, message: '请输入删除状态!'},
          ]},
        },
        url: {
          add: "/system/sysAgent/add",
          edit: "/system/sysAgent/edit",
        }
      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'agentName','agentNameEn','agentNameAbbr','level','agentOrder','description','mobile','email','address','status','remark','timeDeleted'))
        })
      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            console.log("表单提交数据",formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })
          }
         
        })
      },
      handleCancel () {
        this.close()
      },
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'agentName','agentNameEn','agentNameAbbr','level','agentOrder','description','mobile','email','address','status','remark','timeDeleted'))
      },

      
    }
  }
</script>