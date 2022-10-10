#### Is there a limit on the size of the bundle?

When used to pass information between Android components the bundle is serialized into a binder transaction.
The Binder transaction buffer has a limited fixed size, currently 1MB, which is shared by all transactions in progress for the process. 
Since this limit is at the process level rather than at the per activity level, these transactions 
include all binder transactions in the app such as onSaveInstanceState, startActivity and any interaction with the system. 
When the size limit is exceeded, a TransactionTooLargeException is thrown.

For the specific case of savedInstanceState, the amount of data should be kept small because 
the system process needs to hold on to the provided data for as long as the user can ever navigate back to that activity 
(even if the activity's process is killed). Recommended size less than 50k.

***
#### What is the Binder transaction?
Binder is the main IPC/RPC (Inter-Process Communication) system in Android. 
It allows applications to communicate with each other, and it is the base of several important mechanisms in the Android environment. 
For instance, Android services are built on top of Binder.
Message exchanged with Binder are called binder transactions, they can transport simple data 
such as integers but also process more complex structures like file descriptors, 
memory buffers or weak/strong references on objects.

***
