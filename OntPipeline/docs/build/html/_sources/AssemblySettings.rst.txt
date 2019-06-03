Assembly Settings
=================

.. image:: /img/AssemblySettings.png

Mode [1]_ (Required)
____________________
Choose an assembly mode. 

.. note::
  * Conservative: Conservative mode is least likely to produce a complete assembly but has a very low risk of misassembly.
  * Normal: Normal mode is intermediate regarding both completeness and misassembly risk. 
  * Bold: Bold mode is most likely to produce a complete assembly but carries greater risk of misassembly. 
  * Default: Normal.

Method [1]_ (Required)
______________________
Choose an assembly method.

.. note::
  * Long-read-only assembly: Long-read-only assembly using only long reads.
  * Hybrid assembly: Hybrid assembly using both Illumina reads and long reads. 
  * Default: Hybrid assembly.

.. image:: /img/AdvancedAssemblySettings.png

VCF [1]_ (Optional)
___________________
Produce a VCF by mapping the short reads to the final assembly if selected.

.. note::
  * Default: not selected.
  
.. [1] Unicycler https://github.com/rrwick/Unicycler