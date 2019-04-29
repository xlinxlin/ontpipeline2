Input File Structure
====================
Start from Basecalling
______________________
Start the pipeline from Basecalling.

::
   
    YourWorkSpace/
    ├── 1*          
    │   ├── *.fast5
    │   └── ......
    ├── 2*
    │   ├── *.fast5
    │   └── ......  
    ├── 1*_read1*.fastq(.gz)
    ├── 1*_read2*.fastq(.gz)          
    ├── 2*_read1*.fastq(.gz)
    └── 2*_read2*.fastq(.gz)

Start from Reads Filter
_______________________
Start the pipeline from Reads Filter.

If you need adapter trimming
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

::
   
    YourWorkSpace/
    ├── 1*          
    │   ├── barcode01
    |   |   ├── *.fastq
    |   |   └── ......
    │   ├── barcode02
    |   |   ├── *.fastq
    |   |   └── ......
    │   ├── ......
    |   |   ├── *.fastq
    |   |   └── ......
    │   └── unclassified
    |       ├── *.fastq
    |       └── ......
    ├── 2*
    │   ├── barcode01
    |   |   ├── *.fastq
    |   |   └── ......
    │   ├── barcode02
    |   |   ├── *.fastq
    |   |   └── ......
    │   ├── ......
    |   |   ├── *.fastq
    |   |   └── ......
    │   └── unclassified
    |       ├── *.fastq
    |       └── ......
    ├── 1*_read1*.fastq(.gz)
    ├── 1*_read2*.fastq(.gz)          
    ├── 2*_read1*.fastq(.gz)
    └── 2*_read2*.fastq(.gz)

If you do not need adapter trimming
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
::
   
    YourWorkSpace/
    ├── 1*          
    │   ├── *.fastq
    │   └── ......
    ├── 2*
    │   ├── *.fastq
    │   └── ......
    ├── 1*_read1*.fastq(.gz)
    ├── 1*_read2*.fastq(.gz)          
    ├── 2*_read1*.fastq(.gz)
    └── 2*_read2*.fastq(.gz)

Start from Assembly
___________________
Start the pipeline from Assembly.

::
   
    YourWorkSpace/
    ├── 1*          
    │   ├── *.fastq
    │   └── ......
    ├── 2*
    │   ├── *.fastq
    │   └── ......
    ├── 1*_read1*.fastq(.gz)
    ├── 1*_read2*.fastq(.gz)          
    ├── 2*_read1*.fastq(.gz)
    └── 2*_read2*.fastq(.gz)
    
Start from Polishing
____________________
Start the pipeline from Polishing.
::
   
    YourWorkSpace/
    ├── 1*          
    │   ├── *.fastq
    │   └── ......
    ├── 2*
    │   ├── *.fastq
    │   └── ......
    ├── 1*_read1*.fastq(.gz)
    ├── 1*_read2*.fastq(.gz)          
    ├── 2*_read1*.fastq(.gz)
    └── 2*_read2*.fastq(.gz)
