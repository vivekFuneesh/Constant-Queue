
package Full_Immutable;


/**
 *
 * @author vivek
 */


import java.util.NoSuchElementException;




/*********************<***************************************************************************************>***********
 * *********************************************
 * *****************ABSTRACT*********************
 * **********************************************
 * 
 * This Programm is executing in << LESS THAN |_O(n/2)_| Amortized Time Complexity IN WORST CASES of Enqueue() operation>>
 *                                 and <<O(1) Time Complexity >>for each  <<dequeue(), size() and peek() operations...IN ALL CASES>>
 *         
 * 
 * 
 *<< This Programm IS GIVING A FIXED TIME COMPLEXITY.....for dequeue,peek,and size operations>>
 * 
 * *********--------------******
 * *********IMPORTANT--NOTE******
 * *********--------------******
 *      i.e. ****!!!!!!!!!!!!!!****
 *     !!!!!!!!** Developer:Vivek Mangla **!!!!!!!!!!
 *             ****!!!!!!!!!!!!!!!***>>There could be other programms also which are more fast.
 * *********--------------******
 * *********END--IMPORTANT--NOTE******
 * *********--------------******
 * 
 * 
 * AlgoRithM::::::
 * 
 * According to the Immutable Queue Concept******
 * 
 * If suppose there is an object q representing an Immutable Queue
 *             Now if we perform an enQueue() operation then,
 *                             this object will STILL Represent The Previous Queue
 *                             and a new Object q1 of Immutable Queue will be representing 
 *                                 new Queue with 1 extra element then previous one.
 *                      *************similar argument for dequeue() operation*************************
 * 
 *     *******It MEANS THAT THERE IS NO BOUNDATION ON MEMORY FOR an OBJECT..
 * 
 *                i.e.  it is NOT NECESSARY that  a new Object MUST HAVE A 
 *                        WHOLE NEW MEMORY SPACE Of New Queue it is representing.********
 * 
 * <<****BUT If we are EnQueing a value in previous persistent Object MORE_THAN_ONCE than we have to allocate a Whole new Memory Space
 *   ****  >>
 * 
 * Using the Above CONCEPT ...
 * 
 * I have created an algorithm to make a Linked List to work like an Immutablequeue(****IT's My INDEPENDENTLY_AND_SELF DEVELOPED ALGORITHM****)
 * 
 * In this Algorithm,
 *       A new Object may be using  Same Memory Space as Previous One's 
 *         but with certain RESTRICTIONS so that....<<<<.....It is NOT going to CONTRADICT ABOVE CONCEPTS...>>>>>
 *       And Those <Restrictions> are::
 *                                     <1>..<Current Queue Object's front and rear are not to be modified >
 *                                     <2>..<Current Queue Object's SIZE is not to be Modified>
 *       And Hence <Modifications> will be done only on::
 *                                      <1>..<Previous Linked List node's next value ..(If necessary)>
 *                                      <2>..<new Linked List node's data value>
 *                                      <3>..<new Queue Object's rear,front and SIZE value>
 *                                      <4>..<In worst case insert whole Queue of Current Object for  new Object >
 * 
 * <<WHERE rear is a reference to last element of Linked_List and front is First element of Linked_List>>
 * 
 * <<...NOTE::!!!!THE CURRENT QUEUE OBJECT'S Variable Values Are Not Modified At ALL...!!!!>>
 * 
 **************************<********************************************************************************>************* 
 */




public class FULL_IMMUTABLE<E>{
    
    
    /*************************<***********************************************************************>***********************
     * The Object of this class is having 3 Variables...
     * 
     * 1.> front  :: for keeping track of head of this Object..
     * 2.> rear   :: for keeping track of end of this Object..
     * 3.> SIZE   :: for keeping track of number of elements of this Object..
     *********************<*******************************************************************************>******************* 
     */
    
    
    List front=null,rear=null;
    int SIZE=0;
    
    
    public FULL_IMMUTABLE(){}
  
    /************************************<*********************************************>**************************************
     ********************************************************************
     * enqueue(E e) operation 
     ********************************************************************
     * 
     * if(parameter passed is null){
     *                               THROW ILLEGAL_ARGUMENT_EXCEPTION ;
     *                             }
     * 
     * else if(it is not null){
     * 
     *                         Create a new List Node.. List list=new List();
     *                         Now data part of this list contains value passed in parameter ;
     *                         Create a new Immutable Queue Object..FULL_IMMUTABLE<E> FULL_IMMUTABLE=new FULL_IMMUTABLE<E> ;
     *                         Now ,
     *                           if((current Object's front is null)OR
     *                                 (it's front is just one step ahead of it's rear i.e.
     *       <this object has been created by removing last element from another object whose rear  was in somewhere middle of list>)){
     *                                           new Object's front is equal to new List Node formed ;
     *                                           new Object's rear is equal to new List Node Formed ;
     *                                                             }
     * 
     *                           else if(this object's rear is  referring to last element){
     *                                     new Object's front is equal to current Object's front ;
     *                                     new Object's rear is equal to new List Node Formed ;
     *                                }
     *                           else{
     *                            << Create a Duplicate Queue of Current Object and adjust new Object's rear and front references>>
     *                           }
     *                           
     *                           new Object's SIZE is 1 More than current Object's SIZE ;
     *                          
     *                          }
     * 
     * return null;
     *****************************************<******************************************>************************************                      
     */
    
    
    /************************<****************************************************************>************
     * <<TIME_COMPLEXITY>>
     *              <1.>..<Best Case::>  O(1)
     *              <2.>..<Worst Case::> Less Than |_O(n/2)_|<< WHERE, n IS no. of elements enqueued>> 
     * <<****FOR CALCULATION OF TIME COMPLEXITY SEE END OF THIS PROGRAMM****>>
     ***************************<***********************************************************>****************
     */
    
    
    
    @SuppressWarnings("unchecked")
     public FULL_IMMUTABLE<E> enqueue(E e){
         
        if(e==null)throw new IllegalArgumentException();/** <<YOU_MAY_HANDLE_THIS_EXCEPTION_ON_YOUR_OWN>> **/
            
            List list=new List();
            list.object=e;
            FULL_IMMUTABLE<E> FULL_IMMUTABLE=new FULL_IMMUTABLE<E>();
            if((front==null)||(rear.next==front)){FULL_IMMUTABLE.rear=FULL_IMMUTABLE.front=list;}
            else if (rear.next==null){
                FULL_IMMUTABLE.front=front;
                FULL_IMMUTABLE.rear=list;
                rear.next=list;
            }
            else{
                list=null; /*Deallocate Memory from list and then Copy by maintaining Immutability Concept*/
                List p=front;
                while(p!=rear.next){
                    FULL_IMMUTABLE=FULL_IMMUTABLE.enqueue((E)p.object);
                    p=p.next;
                }
                FULL_IMMUTABLE=FULL_IMMUTABLE.enqueue(e);
            }
            
            FULL_IMMUTABLE.SIZE=SIZE+1;
        
            return FULL_IMMUTABLE;
        
     }
    
    
    /**************************<*************************************************************************************>********
     * *********************************
     * dequeue() Operation
     * *********************************
     * 
     * Now Dequeue operation is little bit TRICKY..
     * Because We have to remove first element BUT CURRENT OBJECT's Bounds MUST NOT BE CHANGED
     * SO,
     * 
     * if(current's front is null i.e. EMPTY OR..... if it's rear.next is  referring to it's front i.e. 
     *                                 previous object was containing single item and then a dequeue operation was performed
     *                                 on it and allocated to current object){
     *                                                                         THROW EXCEPTION ;
     *                                                                       }
     * 
     *                                       Create a new Immutable Queue Object;
     *                                       it's front will refer to current's front.next;
     *                                       it's rear will refer to current's rear;
     *                                       it's SIZE will be 1 LESS than current's SIZE;
     *                                       return this new Object;
     * 
     * *****************************<******************************************************************************>***********
     */
    
    /***********************<*************************************************************************************>
     * <<Time_Complexity>>..
     *                   <O(1)...in ALL CASES>
     ************************<************************************************************************************>*
     */
    
    
    @SuppressWarnings("unchecked")
     public FULL_IMMUTABLE<E> dequeue(){
        
        if((front==null)||(rear.next==front)){
            rear=null;front=null;
            throw new NoSuchElementException();
            /** <<YOU_MAY_HANDLE_THIS_EXCEPTION_ON_YOUR_OWN>> **/
        }
       
        FULL_IMMUTABLE<E> FULL_IMMUTABLE=new FULL_IMMUTABLE<E>();
        FULL_IMMUTABLE.front=front.next;
        FULL_IMMUTABLE.rear=rear;
        FULL_IMMUTABLE.SIZE=SIZE-1;
       
         return FULL_IMMUTABLE;
       
     }
    
    
    /******************************<********************************************************************************>**********
     ***********************
     * peek() Operation
     ***********************
     * 
     * if(current's front is null i.e. EMPTY OR..... if it's rear is  refering to it's front i.e. 
     *                                 previous object was containing single item and then a dequeue operation was performed
     *                                 on it and allocated to current object){
     *                                                                         THROW EXCEPTION ;
     *                                                                       }
     * 
     *                                       return current Object's front.object ;
     * 
     *****************************<**********************************************************************************>********
     */
    
     /**
     * <<Time_Complexity>>..
     *                   <O(1)...in ALL CASES>
     ****************************<*************************************************************************************>
     */
    
    
     @SuppressWarnings("unchecked")
     public E peek(){
         if((front==null)||(rear.next==front))throw new NoSuchElementException();/** <<YOU_MAY_HANDLE_THIS_EXCEPTION_ON_YOUR_OWN>> **/
         
         return (E)front.object;
         
        
     }
    
     
     /**************************<**********************************************************************************>***********
      *<************************
      * size() Operation
      *************************>
      *                                                 return SIZE ;
      * 
      *************************<**********************************************************************************>************ 
      * 
      */
     
    /*******************************************<****************************************>*************************************
     *
     * <<Time_Complexity>>..
     *                   <O(1)...in ALL CASES>
     *******************************************<******************************************>***********************************
     */
     
     public int size(){
             
             return SIZE;
         
        
    }
     
      
}

/*****************************<**************************************************************************************>****
 ****************************************************
 * Linked List Has Been Used To Store Pushed Element.
 * class List is used to declare Linked list  Node.
 * This class is also a Generic Class to support Generic data Types.
 ****************************************************
 * 
 * It has 2 variables::
 *     1.> A Generic Reference variable   E object;
 *     2.> A  next reference which refers to next node  List next=null;
 ****************************<**************************************************************************************>***** 
 */


class List<E>{
    
    
    E object;/**<<Node Data Part Can Contain Any Object>>**/
    List next=null;/***<<Reference to next Node>>**/
}

/*************************************************************************************************************************
 *******************************************************************************
 *<< YOU_MAY_REFER_TO_COMPLEXITY.pdf_SCANNED_PAPER_DESCRIBING_PROPERLY_THE_TIME_COMPLEXITY_CALCULATIONS>>
 *
 * Initially q1=initial Queue Object ,created after enQueuing q which does not contain any element..
 * 
 * Suppose after m-1 enqueue Operations in O(1) <<on newly Objets Formed and those are assinged back to q1>> ,
 *     mth enqueue operation is Performed on q2.
 * 
 * Now, if q1 is Enqueued again(<<BECOMING_THE_WORST_CASE>>)
 *         Then, all THe Elements of q1 will be copied into new Object Formed i.e. q3 ,
 *           requiring m-1 copy operations and 1 operation for enqueuing new element.
 *                    
 *       Now,if above is performed n-m times i.e. q1 IS ENQUEUED n-m TIMES..afer it has m elements 
 *            
 *      the <<Total number of Copy_OR_ENQUEUE Operations=m+(n-m)(m) >>   
 *              Total number of Elements Enqueued=n
 *      So,  number of Amortized Operations=(m+(n-m)(m))/(n)
 *                                                  
 *                                                  =(m+nm-m^2)/(n)
 * 
 *             Now,Above Term Will Always Be Less Than m<<(FOR PROOF REFER COMPLEXITY.pdf)>>
 * 
 *          So,Time_Complexity will always be less Than O(m),
 *                where m=length of Main Queue <<For MORE CLARITY ON **length of Main Queue** Refer COMPLEXITY.pdf>>  
 *                
 ********************************************************************************************************************         
 *               
 *<<
 *
 *            ******************************
              *
              ********IMPORTANT_INFO**********
              *
              ****DEVELOPER::VIVEK_MANGLA*****
              *
              *   vivek.funeesh@gmail.com
              *
              ********END_IMPORTANT_INFO******
              *
              *
              *
              ********************************
 *
 *
 *
 *
 *
 *
 *>>
 *
 *******************************************************************************
 ************
 */
