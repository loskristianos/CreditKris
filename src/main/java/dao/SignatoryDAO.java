package dao;

import account.Signatory;

public class SignatoryDAO extends DAO{
    Signatory signatory;
    public SignatoryDAO(Signatory signatory) {
        super(signatory);
        this.signatory = signatory;
    }

    public void write(){
        tableName = "signatories";
        sqlStatement = super.prepareInsertStatement();
        super.write();
    }
}
