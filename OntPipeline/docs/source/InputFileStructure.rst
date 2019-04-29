Input File Structure
====================
Start from Basecalling
______________________
Start the pipeline from Basecalling.

::
   
    YourWorkSpace/
    ├── 1          
    │   ├── *.fast5
    │   └── ......
    ├── 2
    │   ├── *.fast5
    │   └── ......  
    ├── 1_read1*.fastq(.gz)
    ├── 1_read2*.fastq(.gz)          
    ├── 2_read1*.fastq(.gz)
    └── 2_read2*.fastq(.gz)

Start from Reads Filter
_______________________
Start the pipeline from Reads Filter.

If you need adapter trimming:
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

::
   
    YourWorkSpace/
    ├── 1          
    │   ├── barcode01
    │   ├── barcode02
    │   ├── ......
    │   └── unclassified
    ├── 2
    │   ├── barcode01
    │   ├── barcode02
    │   ├── ......
    │   └── unclassified
    ├── 1_read1*.fastq(.gz)
    ├── 1_read2*.fastq(.gz)          
    ├── 2_read1*.fastq(.gz)
    └── 2_read2*.fastq(.gz)
