using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Globalization;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;

namespace Wiz
{
    public class SeriesViewModel
    {
        private WebClient webClient = new WebClient();

        private ObservableCollection<KeyValuePair<double, double>> series = new ObservableCollection<KeyValuePair<double, double>>();

        public SeriesViewModel(Uri seriesUri)
        {
            webClient.DownloadStringCompleted += webClient_DownloadStringCompleted;
            webClient.DownloadStringAsync(seriesUri);
        }

        private void webClient_DownloadStringCompleted(object sender, DownloadStringCompletedEventArgs e)
        {
            var result = e.Result;
            var lines = result.Split(new[] { '\r', '\n' }, StringSplitOptions.RemoveEmptyEntries);
            foreach (var line in lines)
            {
                var data = line.Split(new[] { ' ', '\t' });
                var x = Double.Parse(data[0], CultureInfo.InvariantCulture);
                var y = Double.Parse(data[1], CultureInfo.InvariantCulture);
                var xyPair = new KeyValuePair<double, double>(x, y);
                series.Add(xyPair);
            }
        }

        public ObservableCollection<KeyValuePair<double, double>> Series
        {
            get { return series; }
        }
    }
}