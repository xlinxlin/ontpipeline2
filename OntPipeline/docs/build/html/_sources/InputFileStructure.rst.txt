Input File Structure
====================
Start from Basecalling
______________________
Start the pipeline from Basecalling.

::
   
    ONT_Reads_Directory/
    ├── HPz800_20180821_FAJ18422_MN17776_sequencing_run_VIM4_Janina_26844_read_11_ch_171_strand.fast5
    ├── HPz800_20180821_FAJ18422_MN17776_sequencing_run_VIM4_Janina_26844_read_11_ch_203_strand.fast5
    ├── HPz800_20180821_FAJ18422_MN17776_sequencing_run_VIM4_Janina_26844_read_15_ch_344_strand.fast5   
    ├── HPz800_20180821_FAJ18422_MN17776_sequencing_run_VIM4_Janina_26844_read_17_ch_249_strand.fast5
    ├── HPz800_20180821_FAJ18422_MN17776_sequencing_run_VIM4_Janina_26844_read_19_ch_397_strand.fast5
    └── ......
    
    Illumina_Reads_Directory/
    ├── Prefix01_*R1*.fastq.gz
    ├── Prefix01_*R2*.fastq.gz
    ├── Prefix02_*R1*.fastq.gz
    ├── Prefix02_*R2*.fastq.gz
    ├── Prefix03_*R1*.fastq.gz
    ├── Prefix03_*R2*.fastq.gz
    └── ......

Illumina reads files naming structure for each pair: 
Prefix_*R1*.fastq.gz  Prefix_*R2*.fastq.gz

.. note::
  * "Prefix" is the sample name, each pair should has its own prefix.
  * Do not use underscore ("_") in the prefix.
  * "*" means arbitrarily long characters.

Start from Demultiplexing
_________________________
Start the pipeline from Demultiplexing.

::
   
    ONT_Reads_Directory/
    ├── Prefix01.fastq
    ├── Prefix02.fastq
    ├── Prefix03.fastq  
    ├── Prefix04.fastq
    ├── Prefix05.fastq
    └── ......
    
    Illumina_Reads_Directory/
    ├── Prefix01_*R1*.fastq.gz
    ├── Prefix01_*R2*.fastq.gz
    ├── Prefix02_*R1*.fastq.gz
    ├── Prefix02_*R2*.fastq.gz
    ├── Prefix03_*R1*.fastq.gz
    ├── Prefix03_*R2*.fastq.gz
    └── ......


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
