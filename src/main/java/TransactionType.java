//2045 다이어그램에서 enumeration class를 생성하라는 건지 transaction 안에다 정의하라는 건지 몰라서 일단 클래스로 빼둠


public enum TransactionType
{
    Deposit,
    ForeignDeposit,
    Withdraw,
    ForeignWithdraw,
    SendTransfer,
    ReceiveTransfer
}
