Reads Filter Settings
=====================

.. image:: /img/ReadsFilterSettings.png

.. image:: /img/AdvancedReadsFilterSettings.png

Porechop Settings [1]_ (Optional)
_________________________________
Set Porechop options.

.. note::
  * Select "I want to trim adapter" if you want to use Porechop to trim adapters. Defaut: selected.
  * Select "I want to skip splitting reads based on middle adapters" if you do not want to splt reads when an adapter is found in the middle. Default: not selected. 

Read Score (Required)
________________________
Set a minimum average read quality score to filter the reads.

.. note::
  * Default value: 9.
  * If you want to keep all the reads, set the value to 0, or only a postive integer is acceptable.

Read Length (Required)
_________________________
Set a minimum read length to filter reads.

.. note::
  * Default value: 500.
  * If you want to keep all the reads, set the value to 0, or only a postive integer is acceptable.

Head Crop (Required)
_______________________
Set n nucleotides to be trimmed from start of read.

.. note::
  * Default value: 50.
  * If you do not want to crop any reads, set the value to 0, or only a postive integer is acceptable.
 
 
.. [1] Porechop https://github.com/rrwick/Porechop